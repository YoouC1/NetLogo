// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim

import org.nlogo.core.{ ClosedVariable, Let, Syntax, Token }
import org.nlogo.nvm.{ AnonymousReporter, Context, Reporter }

import scala.collection.JavaConversions._

class _reporterlambda(
  argumentNames:       Seq[String],
  val closedVariables: Set[ClosedVariable],
  lambdaSource:        String) extends Reporter {

  source = lambdaSource

  val formals: Seq[Let] = argumentNames.map(name => Let(name))
  val formalsArray: Array[Let] = formals.toArray

  override def report(c: Context): AnyRef = {
    AnonymousReporter(
      body           = args(0),
      formals        = formalsArray,
      binding        = c.activation.binding,
      locals         = c.activation.args,
      source         = lambdaSource)
  }
}
