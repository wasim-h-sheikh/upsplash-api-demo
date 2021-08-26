package com.wasim.unsplashdemo.data.model

import android.graphics.Point
import com.wasim.unsplashdemo.util.SHAPE_RADIUS

data class Circle(
      val center:Point,
    val radius:Float=SHAPE_RADIUS
)
