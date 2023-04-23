<script setup>
import Header from '../components/Header.vue';
import UserContacts from '../components/UserContacts.vue';
import CardUsers from '../components/CardUsers.vue';
</script>
<template>
<main>
    <Header/>
    <UserContacts/>
    <CardUsers/>
</main>
</template>
<style lang="scss">
@use "@/scss/colors" as c;
@use "@/scss/fonts";

main{
  margin: 0 auto;
  width: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
</style>