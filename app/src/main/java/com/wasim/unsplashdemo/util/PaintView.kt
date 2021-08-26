package com.wasim.unsplashdemo.util

import android.R.attr
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.app.Activity

import android.util.DisplayMetrics
import android.R.attr.radius
import com.wasim.unsplashdemo.data.model.Circle
import com.wasim.unsplashdemo.data.model.Square
import java.util.*
import kotlin.collections.ArrayList


const val ACTION_ADD_CIRCLE=1
const val ACTION_ADD_SQUARE=2
const val ACTION_UNDO=3
const val SHAPE_RADIUS=25F
const val SAFE_DISTANCE=5F

class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var drawPaint: Paint? = null
    private var canvas: Canvas? = null
    private var currentPoint:Point?=null
    private var pendingAction:Int=-1
    private val shapeList= LinkedList<Any>()
    private val pointList= LinkedList<Point>()
    private var viewWidth:Int=0
    private var viewHeight:Int=0

    init {
        setupPaint()
    }
    // Setup paint with color and stroke styles
    private fun setupPaint() {
        drawPaint = Paint().apply {
            color=Color.BLUE
            isAntiAlias = true
            strokeWidth=5.0f
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        updateView()
    }

    private fun updateView(){

        shapeList.forEach { shape->
            if (shape is Circle){
                drawPaint?.let {
                    canvas?.drawCircle(shape.center.x.toFloat()+ SHAPE_RADIUS,shape.center.y.toFloat()+ SHAPE_RADIUS, SHAPE_RADIUS, it)
                }
            }else if (shape is Square){
                drawPaint?.let {
                    canvas?.drawRect(shape.left+ SHAPE_RADIUS, shape.top+ SHAPE_RADIUS, shape.right+ SHAPE_RADIUS, shape.bottom+ SHAPE_RADIUS, it)
                }
            }
        }
        updateCenterPoint()
    }

    private fun updateCenterPoint(){
        if (pendingAction== ACTION_UNDO){
           return
        }
        currentPoint?.let { pointList.add(it) }
        currentPoint=RandomPointGenerator.nextPoint(pointList,viewWidth,viewHeight)
    }
    fun takeAction(action:Int){
        pendingAction=action

        when (action){
            ACTION_ADD_CIRCLE->
                currentPoint?.let {
                    shapeList.add(Circle(it))
                }


            ACTION_ADD_SQUARE->
                currentPoint?.let { point->
                    shapeList.add(Square(point.x- SHAPE_RADIUS,
                        point.y- SHAPE_RADIUS,
                        point.x+ SHAPE_RADIUS,point.y+ SHAPE_RADIUS)) }


            ACTION_UNDO->removeLastShape()
        }
        //update view i.e. onDraw() will call...
        invalidate()
    }

    private fun removeLastShape() {
        if(shapeList.isEmpty()) {
            return
        }
        shapeList.removeLast()
        //restore the center x,y of last shape
        currentPoint=pointList.removeLast()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewHeight=h
        viewWidth=w

        currentPoint=RandomPointGenerator.nextPoint(pointList,viewWidth,viewHeight)
    }

}