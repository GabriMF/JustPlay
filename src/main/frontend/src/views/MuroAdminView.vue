<script setup>
	import { ref, onBeforeMount } from "vue";
import HeaderAdmin from '../components/HeaderAdmin.vue';
import BannerAdmin from '../components/BannerAdmin.vue';
import Search from '../components/Search.vue';
import PostService from "../services/PostService";
import CardProfile from "../components/CardProfile.vue";

const postService = new PostService();
	let posts = ref([]);
 	onBeforeMount(async()=>{
	await postService.fetchAllPost()
	posts.value = postService.getPost()
	console.log(posts.value)
	});
</script>

<template>
<main>
<HeaderAdmin/>
<BannerAdmin/>
<Search/>

			<CardProfile
			v-for= "post in posts" :post="post"/>

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