<script setup>
import { ref, onBeforeMount } from "vue";
import { useProgrammatic } from "@oruga-ui/oruga-next";
import InfoUser from "../components/InfoUser.vue";
import CardProfile from "../components/CardProfile.vue";
import Header from "../components/Header.vue";
import AddPublication from "../components/AddPublication.vue";
import PostService from "../services/PostService";

let input = ref("");

const postService = new PostService();

let posts = ref([]);

onBeforeMount(async () => {
	await postService.fetchAllPost()
	posts.value = postService.getPost()
	console.log(posts.value)
});

function filteredList() {
	return posts.value.filter((post) =>
		post.title.toLowerCase().includes(input.value.toLowerCase()) ||
		post.description.toLowerCase().includes(input.value.toLowerCase())
		// ||profile.name.toLowerCase().includes(input.value.toLowerCase())
	);
}


const trapFocus = ref(false);
const { oruga } = useProgrammatic();

function cardModal() {
	oruga.modal.open({
		component: AddPublication,
		trapFocus: true,
	});
}

</script>

<template>
	<main>
		<Header />
		<InfoUser />
		<div class="title">
			<div class="text">
				<h2>My Events</h2>
			</div>
			<div class=" modal-container">
				<o-button @click="cardModal()" class="modal">
					<i class="fa-solid fa-plus btn-add"></i>
				</o-button>
			</div>
		</div>
		<div class="publi">
			<CardProfile v-for="post in posts" :post="post" />
		</div>
	</main>
</template>

<style lang="scss" scoped>
@use "@/scss/fonts";

.title {
	width: 46vw;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-top: 1vh;
	margin-left: 12vw;

	.text {
		font-size: xx-large;
		color: black;
		font-family: "Open Sans", sans-serif;
		font-weight: bold;
	}

	.modal-container {
		display: flex;
		align-items: center;

		.modal {
			background: white;
			display: flex;
			margin-left: 5vw;
			width: 5vw;
			border-radius: 5px;
			font-size: 1.2em;
			background-color: #d7d7d7;
			color: black;
			height: 2em;
			&:hover{
				background-color: #a7a7a7;
			}
			&:active{
				background-color: #555555;
			}
		}
	}

}

main {
	margin: 0 auto;
	width: 80%;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;

	.publi {
		display: grid;
		grid-template-columns: repeat(3, 1fr);
		gap: 1em;
		justify-content: center;
		align-items: center;
	}
}
</style>
