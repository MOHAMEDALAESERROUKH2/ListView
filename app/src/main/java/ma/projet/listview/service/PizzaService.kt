package ma.projet.listview.service

import ma.projet.listview.classes.Pizza
import ma.projet.listview.dao.IDao

class PizzaService private constructor():IDao<Pizza>{
    private val pizzas:MutableList<Pizza>
    init {
        pizzas=ArrayList()
    }

    override fun create(o: Pizza): Boolean {
        return pizzas.add(o)
    }

    override fun delete(o: Pizza): Boolean {
        return pizzas.remove(o)
    }

    override fun update(o: Pizza): Boolean {
        for(p in pizzas ){
            if (p.id==o.id){
                p.nom=o.nom
                p.description=o.detailsIngred
                p.duree=o.duree
                p.photo=o.photo
                p.nbrIngredients=o.nbrIngredients
                p.preparation=o.preparation
                return true
            }
        }
        return false
    }

    override fun findById(id: Int): Pizza? {
        for (p in pizzas){
            if (p.id==id){
                return p
            }
        }
        return null
    }

    override fun findAll(): List<Pizza> {
        return pizzas
    }

    companion object{
        var instance:PizzaService?=null
        get(){
            if(field==null)field= PizzaService()
            return field
        }
        private set
    }

}