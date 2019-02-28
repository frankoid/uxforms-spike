package com.example.form

import java.util.Locale

import com.example.form.build.MyFormDefinitionBuildInfo
import com.uxforms.domain.constraint.Required.required
import com.uxforms.domain.{FormDefinition, FormDefinitionFactory, ResourceBundleMessages}
import com.uxforms.dsl.containers.mustache.Section.section
import com.uxforms.dsl.helpers.FormDefinitionHelper._
import com.uxforms.dsl.widgets.Input.inputText

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object MyFormDefinitionFactory extends FormDefinitionFactory with TemplateLoader {

  /**
    * This is which locales are supported by your form, in order of preference.
    * So if your user doesn't explicitly state which locale they want the form in, they
    * will get the first in this sequence.
    */
  override val supportedLocales: Seq[Locale] = Seq(Locale.UK)

  /**
    * Makes my classLoader available implicitly so that message bundles can be referenced
    * easily.
    */
  implicit val localClassLoader = getClass.getClassLoader


  /**
    * Factory method for instantiating your form definition.
    */
  override def formDefinition(requestedLocale: Locale)(implicit executionContext: ExecutionContext): FormDefinition = {

    /**
      * Resolves the locale requested by the user from a combination of their HTTP headers,
      * explicitly requested locale (i.e. in the URL), and those supported by this form definition.
      */
    implicit val locale = resolveRequestedLocale(requestedLocale)

    implicit val formLevelMessages: ResourceBundleMessages = "formMessages"

    /**
      * This is where the questions for your form are defined.
      */
    formDef(

      MyFormDefinitionBuildInfo.name,

      Duration(MyFormDefinitionBuildInfo.retentionPeriod),

      completedSection("completedMessages"),

      section(
        "firstSectionMessages",

        inputText("elementName", "elementName.label" -> "This is the question text that will appear in the form", required)
      )
    )

  }

}
