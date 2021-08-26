package com.wasim.unsplashdemo.util

import android.graphics.Point
import kotlin.random.Random

class RandomPointGenerator {
    companion object{

    fun nextPoint(points:List<Point>,limitX:Int,limitY:Int):Point{
        val randomX= Random((limitX- SHAPE_RADIUS).toInt())
        val randomY= Random((limitY- SHAPE_RADIUS).toInt())
        var randX:Int
        var randY:Int
       do{
           randX=randomX.nextInt(0,(limitX- SHAPE_RADIUS).toInt())
        }while (isNotUniqueRandom(points,randX,true))

        do{
            randY=randomY.nextInt(0,(limitY- SHAPE_RADIUS).toInt())
        }while (isNotUniqueRandom(points,randY,false))

        return Point(randX,randY)
    }
    private fun isNotUniqueRandom(points:List<Point>,rand:Int,isX:Boolean):Boolean{
        if(points.isEmpty()){
            return false
        }
        points.forEach{
            if (isX){
                if (rand == it.x){
                    return true
                }
            }else{
                if (rand == it.y){
                    return true
                }
            }
        }
        return false
    }

    }
}