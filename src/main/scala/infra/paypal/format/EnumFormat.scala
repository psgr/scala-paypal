package infra.paypal.format

import play.api.libs.json._

/**
 * @author alari (name.alari@gmail.com)
 * @since 21.10.13 15:28
 */
private[paypal] trait EnumFormat {
  self: Enumeration ⇒

  protected def valueFormat = new Format[this.type#Value] {
    def reads(json: JsValue) = json match {
      case s: JsString ⇒
        values.find(_.toString == s.value) match {
          case Some(v) ⇒ JsSuccess(v, __)
          case None    ⇒ JsError("Incorrect value")
        }
      case _ ⇒
        JsError("Incorrect value type")
    }

    def writes(o: Value): JsValue = JsString(o.toString)
  }

}
