<script setup>
import axios from 'axios';
import { defineProps, ref } from 'vue';

let random = Math.round(Math.random() * 2 + 1);
// let image = "src/assets/images/UserNamePostBackground/background"+ random + ".png";

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

</script>

<template>
	<!-- <div class="cards"> -->
	<div class="card">
		<div class="info">
			<div class="headerCard">
				<h3 class="userNamePost">
					Rick Sanchez
				</h3>
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
							<v-card-title style="background-color: rgb(177, 5, 5);">
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
				<!-- <button class="button-edit">
						<i class="fa-solid fa-pen btn btn-edit"></i>
					</button> -->
				<button class="button-delete">
					<i @click="deletePost" class="fa-solid fa-trash btn btn-delete"></i>
				</button>
			</div>

		</div>
	</div>
	<!-- </div> -->
</template>

<style lang="scss" scoped>
@use "@/scss/colors" as c;
@use "@/scss/fonts";

// .cards {
// }

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
		// justify-content: space-between;
		border-radius: 5px;

		.text {
			.titlePubli {
				margin-left: 1vw;
				font-size: 1.5vw;
				color: map-get(c.$colors, "black");
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

				// &:active {
				// 	width: 50%;
				// }
			}
		}

	}

	.popUp {
		width: 90vw;

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
			color: map-get(c.$colors, "grey");
			font-family: "openSans";
		}

		.cerrarButton {
			width: 10vw;
			margin-right: 2vw;

			&:hover {
				background-color: #9c93b6;
				border-radius: 5px;
			}

			&:active {
				background-color: purple;
				border-radius: 5px;
			}
		}
	}

	.v-card {
		background-color: grey;
	}

	.v-card-title {
		background-color: rgb(177, 5, 5);

		.titlePubliBigger {
			background-color: rgb(177, 5, 5);
			margin-left: 1vw;
			font-size: 1.5vw;
			color: map-get(c.$colors, "black");
			font-family: "openSans";
			font-weight: 600;
		}
	}

	.buttons {
		display: flex;
		justify-content: flex-end;
		margin-top: 2vh;

		.verMasButton {
			width: 8vw;
			height: 4vh;

			&:hover {
				background-color: map-get(c.$colors, "light-purple");
				border-radius: 5px;
			}

			&:active {
				background-color: purple;
				border-radius: 5px;
			}
		}


		.button-edit {
			margin: 0.3em;
			width: 2em;
			height: 2em;
			align-items: center;
			display: flex;
			justify-content: center;

			&:hover {
				background-color: map-get(c.$colors, "light-purple");
				border-radius: 5px;
			}

			&:active {
				background-color: purple;
				border-radius: 5px;
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
				background-color: map-get(c.$colors, "light-purple");
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