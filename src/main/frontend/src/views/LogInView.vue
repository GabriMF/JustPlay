<script setup>
import HeaderLogIn from '../components/HeaderLogIn.vue'
import LogInComp from '../components/LogInComp.vue'

</script>

<template>
    <main class="main">
        <HeaderLogIn />
        <LogInComp />
    </main>
</template>

<style scoped lang="scss">
.main {
    background-color: rgb(56, 56, 56);
    width: 100vw;
    height: 100vh;
}
</style>