<script setup lang="ts">
import { ref } from 'vue'

const backendLink = 'http://localhost:8000'

const searchQuery = ref('')
const searchResults = ref<unknown>(null)
const search = async () => {
  const response = await fetch(`${backendLink}/search?query=${encodeURIComponent(searchQuery.value)}`)
  const data = await response.json()
  searchResults.value = data
}

const username = ref('')
const password = ref('')
const createUserMessage = ref('')
const createUser = async () => {
  const response = await fetch(`${backendLink}/createUser`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      username: username.value,
      password: password.value
    })
  })
  const data = await response.json()
  createUserMessage.value = response.ok ? 'User created' : (data.error ?? 'Could not create user')
  if (response.ok) {
    username.value = ''
    password.value = ''
  }
}
</script>

<template>
  <h1>SQL Injection CTF</h1>

  <h2>Search users</h2>
  <input type="text" v-model="searchQuery">
  <button @click="search">Search</button>
  <pre>{{ JSON.stringify(searchResults, null, 2) }}</pre>

  <h2>Create user</h2>
  <input type="text" v-model="username" placeholder="Username">
  <input type="text" v-model="password" placeholder="Password">
  <button @click="createUser">Create</button>
  <p>{{ createUserMessage }}</p>
</template>

