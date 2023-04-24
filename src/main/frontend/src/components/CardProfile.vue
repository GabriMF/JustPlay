<script setup>
import axios from 'axios';
import { defineProps, ref } from 'vue';

let date = new Date().toLocaleDateString();

window.addEventListener("DOMContentLoaded", () => {
	const buttonDelete = document.querySelector(".button-delete");
	const buttonEdit = document.querySelector(".button-edit");
	const title = document.querySelector(".titlePubli");
	const text = document.querySelector(".textPubli");

	buttonDelete.addEventListener("click", () => {
		const publicacion = buttonDelete.closest(".card");
		publicacion.remove();
	});

	buttonEdit.addEventListener("click", () => {
		title.contentEditable = true;
		text.contentEditable = true;
		file.contentEditable = true;
		buttonEdit.style.display = "none";
		const buttonSave = document.createElement("button");
		buttonSave.textContent = "Guardar";
		buttonSave.classList.add("button-save");
		buttonEdit.parentNode.insertBefore(
			buttonSave,
			buttonEdit.nextSibling
		);
		buttonSave.addEventListener("click", () => {
			title.contentEditable = false;
			text.contentEditable = false;
			buttonSave.parentNode.removeChild(buttonSave);
			buttonEdit.style.display = "block";
		});
	});
});

const props = defineProps({
	post: Object,
})

const deletePost = () => {
	const idPost = props.post.id;
	axios({
		method: "DELETE",
		url: "http://localhost:8080/api/posts/" + idPost,
		withCredentials: true,
	});
	location.reload()
};

const dialog = ref(false);

// let bandName = ref()

// onBeforeMount(() => {
// 	axios({
// 		method: "GET",
// 		url: "http://localhost:8080/api/profiles/" + props.post.idProfile,
// 		withCredentials: true,
// 	})
// 		.then((response) => {
// 			profiles.value = response.data.name;
// 			console.log(profiles.value);
// 		})
// 		.catch((e) => {
// 			console.log(e);
// 			console.log(props.post.idProfile);
// 			console.log("Catch error profiles");
// 		});
// });

</script>

<template>
	<!-- <div class="cards"> -->
	<div class="card">
		<div class="headerCard">
			<h3 class="userNamePost">{{ bandName }}</h3>
			<p class="date">{{ date }}</p>
		</div>
		<div class="publication">
			<div class="text">
				<h2 class="titlePubli">{{ post.title }}</h2>
				<p class="textPubli">{{ post.description }}</p>
			</div>
			<div class="picPublication">
				<img class="filePubli" v-if="post.image" :src="'http://localhost:8080/media/' + post.image"
					alt="imagen post" />
			</div>
		</div>
		<div class="buttons">
			<v-row class="mr-1" justify="end">
				<v-dialog class="popUp" v-model="dialog">
					<template v-slot:activator="{ props }">
						<v-btn class="verMasButton" variant="text" v-bind="props">
							Ver más
						</v-btn>
					</template>
					<v-card style="background-color: rgb(83, 83, 83);">
						<v-card-title style="background-color: rgb(177, 5, 5); font-weight: bolder;">
							<h2 class="titlePubliBigger">{{ post.title }}</h2>
						</v-card-title>
						<v-card-text style="background-color: grey;">
							<p class="textPubliBigger">{{ post.description }}</p>
							<img class="popupPic" v-if="post.image" :src="'http://localhost:8080/media/' + post.image"
								alt="imagen post" />
							<p class="datePopUp">{{ date }}</p>

						</v-card-text>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn class="cerrarButton" variant="text" @click="dialog = false">
								Cerrar
							</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</v-row>
			<button class="button-delete">
				<i @click="deletePost" class="fa-solid fa-trash btn btn-delete"></i>
			</button>
		</div>
	</div>
	<!-- </div> -->
</template>

<style lang="scss" scoped>
@use "@/scss/fonts";

.card {
	display: flex;
	flex-direction: column;
	margin-bottom: 3vh;
	width: 30vw;
	height: 42vh;
	background-color: grey;

	.headerCard {
		display: flex;
		justify-content: space-between;
		align-items: flex-end;
		background-color: rgb(177, 5, 5);

		.userNamePost {
			display: flex;
			align-items: center;
			font-size: 3vh;
			font-weight: bolder;
			margin-left: 2vw;
			color: white;
		}

		.date {
			align-self: flex-end;
			margin-right: 2vw;
			color: black;
			font-family: "openSans";
		}
	}

	.publication {
		display: flex;
		flex-direction: column;
		border-radius: 5px;

		.text {
			.titlePubli {
				margin-left: 1vw;
				font-size: 1.5vw;
				color: black;
				font-family: "openSans";
				font-weight: 600;
			}

			.textPubli {
				font-family: "openSans";
				padding: 0.5em;
			}
		}

		.picPublication {
			.filePubli {
				max-height: 20vh;
				padding: 5px;
			}
		}

	}

	.buttons {
		display: flex;
		justify-content: flex-end;
		margin-top: 2vh;

		.popUp {
			width: 90vw;

			.verMasButton {
				width: 8vw;
				height: 4vh;

				&:hover {
					background-color: #CBBFEB;
					border-radius: 5px;
				}

				&:active {
					background-color: purple;
					border-radius: 5px;
				}
			}

			.v-card {
				background-color: grey;


				.v-card-title {
					background-color: rgb(177, 5, 5);

					.titlePubliBigger {
						background-color: rgb(177, 5, 5);
						margin-left: 1vw;
						font-size: 1.5vw;
						color: black;
						font-family: "openSans";
					}
				}

				.textPubliBigger {
					font-family: "openSans";
					padding: 0.5em;
				}

				.popupPic {
					height: 50vh;
				}

				.datePopUp {
					display: flex;
					justify-content: flex-end;
					margin-right: 2vw;
					color: rgb(98, 98, 102);
					font-family: "openSans";
				}

				.cerrarButton {
					width: 10vw;
					margin-right: 2vw;

					&:hover {
						background-color: #514c5e;
						border-radius: 5px;
					}

					&:active {
						background-color: purple;
						border-radius: 5px;
					}
				}
			}
		}

		.button-delete {
			margin-right: 1vw;
			width: 1vw;
			height: 1vh;
			align-items: center;
			display: flex;
			justify-content: center;

			&:hover {
				background-color: #CBBFEB;
				border-radius: 5px;
			}

			&:active {
				background-color: purple;
				border-radius: 5px;
			}
		}

		.btn {
			font-size: 1em;
		}
	}
}
</style>

