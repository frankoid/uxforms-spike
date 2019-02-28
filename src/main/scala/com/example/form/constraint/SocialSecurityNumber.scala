package com.example.form.constraint

import java.util.Locale

import com.uxforms.domain.constraint.Constraint
import com.uxforms.domain.constraint.MatchesRegex.matchesRegex
import com.uxforms.dsl.helpers.MessageHelper

object SocialSecurityNumber extends MessageHelper {

  def socialSecurityNumber(implicit l: Locale, c: ClassLoader): Constraint = matchesRegex("""[A-Za-z]{2}[0-9]{6}[A-Za-z0-9]?""".r)("socialSecurityNumberConstraintMessages")
}
