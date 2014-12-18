package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models._

object Application extends Controller {

  val itemForm = Form(
    tuple(
      "name" -> nonEmptyText,
      "price" -> number,
      "importance" -> nonEmptyText
    )
  )

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def getList() = Action {
    Ok(views.html.list(PreciousItem.all(), itemForm))
  }

  def createItem() = Action {implicit request =>
    itemForm.bindFromRequest.fold(
      errors => BadRequest(views.html.list(PreciousItem.all(), errors)),
      data => {
        PreciousItem.createItem(data._1, data._2, data._3)

        Redirect(routes.Application.getList)
      }
    )
  }

  def deleteItem(id : Int) = Action {
    PreciousItem.deleteItem(id)
    Redirect(routes.Application.getList)
  }

}
