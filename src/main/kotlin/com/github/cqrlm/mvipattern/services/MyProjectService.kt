package com.github.cqrlm.mvipattern.services

import com.github.cqrlm.mvipattern.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
