package com.belajarbareng

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajarbareng.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var listUsers = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonFile = resources.openRawResource(R.raw.githubuser)
        val dataJson = getJsonObject(jsonFile)
        listUsers = parseJson(dataJson)

//        listUsers = getListDataUsers()

        showRecyclerList()
    }

    private fun getListDataUsers() : ArrayList<User> {
        val dataName = resources.getStringArray(R.array.name)
        val dataPhoto = resources.obtainTypedArray(R.array.avatar)
        val dataLocation = resources.getStringArray(R.array.location)

        val listUsers = ArrayList<User>()
        for (index in dataName.indices) {
            val user = User(
                name = dataName[index],
                photo = dataPhoto.getResourceId(index, -1),
                userLocation = dataLocation[index],
            )
            listUsers.add(user)
        }

        dataPhoto.recycle()
        return listUsers
    }

    private fun getJsonObject(dataJson: InputStream): JSONObject {
        val reader = BufferedReader(InputStreamReader(dataJson))
        val response: StringBuilder = StringBuilder().append(
            reader.readLine()
        )
        var inputStr: String?
        while (reader.readLine().also { inputStr = it } != null)
            response.append(inputStr)
        return JSONObject(response.toString())
    }

    private fun parseJson(jsonObject: JSONObject): ArrayList<User> {
        val dataUser: ArrayList<User> = ArrayList()
        val users = jsonObject.getJSONArray("users")
        for (index in 0 until users.length()) {
            val user = User(
                name = users.getJSONObject(index).getString("name"),
                photo = resources.getIdentifier(
                    users.getJSONObject(index).getString("avatar"),
                    "drawable",
                    packageName
                ),
                userLocation = users.getJSONObject(index).getString("location"),
            )
            dataUser.add(user)
        }
        return dataUser
    }

    private fun showRecyclerList() {
        binding.rvUsers.setHasFixedSize(true)
        val listUserAdapter = ListUserAdapter(listUsers)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                selectedUser(data)
            }
        })
    }

    private fun selectedUser(user: User) {
        val intent = Intent(this, DetailActivity::class.java)
            .putExtra(DATA_USER, user)
        startActivity(intent)
    }

    companion object {
        const val DATA_USER = "DATA_USER"
    }
}