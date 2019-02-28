package com.example.form

import com.example.form.build.MyFormDefinitionBuildInfo
import com.uxforms.dsl.{MustacheRenderEngine, RemoteTemplateResolver}

trait TemplateLoader {
  private val themeName = MyFormDefinitionBuildInfo.themeName
  implicit val renderEngine = new MustacheRenderEngine(new RemoteTemplateResolver(themeName, "templates"))
}
