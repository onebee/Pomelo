package com.one.plugindemo

import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginDemo implements Plugin<Project> {


    @Override
    void apply(Project target) {
        def name = "one bit"
        def extension = new ExtensionDemo()
        def ex = target.project.extensions.create('onebit', ExtensionDemo)

        target.afterEvaluate {

            println "hello ${ex.name}!  "
        }

    }
}


