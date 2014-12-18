package models

// Defines the case class for a single item
case class PreciousItem(name : String, price : Int, importance : String)

// Defines the object (singleton) that stores all items
object PreciousItem {
  // Holds all precious items that are used
  var items : List[PreciousItem] = List()

  // Returns all elements
  def all() : List[PreciousItem] = {
    items
  }

  // Add the new item to the list
  def createItem(name : String, price : Int, importance : String) {
    items = PreciousItem(name, price, importance) :: items
  }

  // Deletes the item at the position id
  def deleteItem(id : Int) {
    items = items.take(id) ::: items.drop(id + 1)
  }
}
