package ma.projet.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import ma.projet.listview.classes.Pizza
import ma.projet.listview.databinding.ActivityPizzaDetailBinding
import ma.projet.listview.service.PizzaService

class PizzaDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPizzaDetailBinding
    lateinit var pizza_name: TextView
    lateinit var description: TextView
    lateinit var ingredient: TextView
    lateinit var image: ImageView
    lateinit var ps: PizzaService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPizzaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        image = findViewById(R.id.imageView5)
        pizza_name = findViewById(R.id.pizza_name)
        description = findViewById(R.id.description)
        ingredient = findViewById(R.id.ingredient)
        val intent = intent
        ps = PizzaService.instance!!
        val p: Pizza? = ps.findById(Integer.parseInt(intent.getStringExtra("id")))
        if (p != null) {
            pizza_name.text = p.nom
            image.setImageResource(p.photo)
            description.text = p.description
            ingredient.text = p.detailsIngred
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                shareApp()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun shareApp() {
        val msg: String = "hi there, checkout these cool recipes"
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, msg)
        intent.type = "text/plain"
        startActivity(intent)
    }
}
