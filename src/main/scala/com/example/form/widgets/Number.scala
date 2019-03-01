package com.example.form.widgets

import com.uxforms.domain.constraint.IsNumber.isNumber
import com.uxforms.domain.constraint.{Constraint, MaxNumber, MinNumber}
import com.uxforms.domain.{ExtraTemplateRenderArgs, Messages, NoExtraRenderArgs, SimpleExtraTemplateRenderArgs}
import com.uxforms.dsl.MustacheRenderEngine
import com.uxforms.dsl.widgets.{Input, WidgetVisibility, alwaysShown}
import play.api.libs.json.{JsObject, Json}

import scala.util.Try

/**
  * A widget for collecting numbers
  *
  * @since 15.16.0
  */
object Number {

  /**
    * Instantiates an [[Input]] with a reporting name and html input type of "number".
    * Also passes in the values of any [[MinNumber]] or [[MaxNumber]] constraints to the template so they can be added to the html input's attributes.
    */
  def inputNumber[M <: Messages[M]](name: String, messages: Messages[M], cons: Set[Constraint], step: BigDecimal = 1, args: ExtraTemplateRenderArgs = NoExtraRenderArgs, showMe: WidgetVisibility = alwaysShown)(implicit renderEngine: MustacheRenderEngine): Input[M] = {

    def numberArgs(cons: Set[Constraint]) = SimpleExtraTemplateRenderArgs {
      cons.collect {
        case n: MinNumber[_] => "min" -> n.min
        case n: MaxNumber[_] => "max" -> n.max
      }.toMap ++ Map("step" -> step)
    }


    new Input(name, "number", messages, cons + isNumber(messages), "input.mustache", numberArgs(cons) ++ args, "number", "widgetBoilerplate.mustache", showMe, renderEngine) {
      override def toJson(formPostData: Map[String, Seq[String]]): JsObject = {
        formPostData.get(name).flatMap(_.headOption.flatMap(stringValue => Try(BigDecimal(stringValue)).toOption).map((numberValue: BigDecimal) => Json.obj(name -> numberValue))).getOrElse(Json.obj())
      }
    }
  }

}
