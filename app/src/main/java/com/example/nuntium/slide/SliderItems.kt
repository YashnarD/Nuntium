package com.example.nuntium.slide

class SliderItems {

    private var image:Int ?= null

    constructor(image: Int?) {
        this.image = image
    }

    fun getImage():Int? {
        return image
    }
}