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
    private var currentX:Float=0F
    private var currentY:Float=0F
    private var pendingAction:Int=-1
    private val shapeList= LinkedList<Any>()
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
                    canvas?.drawCircle(shape.x,shape.y, SHAPE_RADIUS, it)
                }
            }else if (shape is Square){
                drawPaint?.let {
                    canvas?.drawRect(shape.left, shape.top, shape.right, shape.bottom, it)
                }
            }
        }
        updateCenterPoint()
    }

    private fun updateCenterPoint(){
        if (pendingAction== ACTION_UNDO){
           return
        }
        currentX+= 2*SHAPE_RADIUS+ SAFE_DISTANCE
        currentY+= 2*SHAPE_RADIUS+ SAFE_DISTANCE

    }
    fun takeAction(action:Int){
        pendingAction=action

        when (action){
            ACTION_ADD_CIRCLE->
                shapeList.add(Circle(currentX, currentY))

            ACTION_ADD_SQUARE->
                shapeList.add(Square(currentX,currentY,currentX- SHAPE_RADIUS,
                    currentY- SHAPE_RADIUS,
                    currentX+ SHAPE_RADIUS,currentY+ SHAPE_RADIUS))

            ACTION_UNDO->removeLastShape()
        }
        //update view i.e. onDraw() will call...
        invalidate()
    }

    private fun removeLastShape() {
        if(shapeList.isEmpty()) {
            return
        }
        val lastShape = shapeList.removeLast()
        //restore the center x,y of last shape
        if (lastShape is Circle) {
            currentX = lastShape.x
            currentY = lastShape.y
        } else if (lastShape is Square) {
            currentX = lastShape.x
            currentY = lastShape.y
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        currentX= 0F//(w / 2).toFloat()
        currentY= 0F//(h / 2).toFloat()
    }

}