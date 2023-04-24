<script setup>
import { ref, reactive, onBeforeMount } from "vue";
import axios from "axios";

import PostService from "@/services/PostService.js"
const onFileChange = event => {
  file.value = event.target.files[0];
};
const file = ref(null);
const postService = new PostService()
const titleModel = ref()
const descriptionModel = ref()
const post = reactive({
  title: titleModel,
  description: descriptionModel,
})
const submitData = async () => {
  try {
    const formData = new FormData();
    formData.append("title", titleModel.value);
    formData.append("description", descriptionModel.value);
    if (file.value != null) {
      formData.append("file", file.value);

      await axios({
        method: "POST",
        url: "http://localhost:8080/media/upload/post",
        data: formData,
        withCredentials: true,
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
    } else {
      await axios({
        method: "POST",
        url: "http://localhost:8080/api/posts/add",
        data: post,
        withCredentials: true,
      });
    }
    console.log("Enviado")
    location.reload();
  } catch (error) {
    console.log(error);
  }
  location.reload()
}
const reload = () => {
  location.reload()
} 
</script>

<template>
  <div class="formBody">
    <div class="addPostHeader">
      <h1 class="addYourPubli">¡Añade una nueva publicación!</h1>
    </div>
    <form @submit.prevent>
      <input v-model="titleModel" class="title" type="text" placeholder="Titulo de tu publicación" />
      <textarea v-model="descriptionModel" class="description" placeholder="Cuentanos algo interesante..." rows="5"
        cols="46">
      </textarea>
      <input class="resources" type="file" @change="onFileChange" ref="fileInput">
      <div class="buttonsContainer">
        <button @click="reload" class="cancelButton">Cancelar</button>
        <button @click="submitData" class="sendButton">Publicar</button>
      </div>
    </form>
  </div>
</template>

<style lang="scss" scoped>

.formBody {
  background: rgb(56, 56, 56);

  @media(min-width: 1023px) and (max-width: 1438px) {
    width: 1000px;
  }

  @media(min-width: 1439px) {
    width: 1000px;
  }

  .addPostHeader {
      background-color: rgb(177, 5, 5);
      display: flex;
      justify-content: center;
      width: 100%;

      .addYourPubli {
        color: white;
        font-size: xx-large;
        font-weight: bold;
      }
    }

  form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-around;
    width: 100%;
    height: 80vh;

    .title {
      background: rgb(185, 185, 185);
      font-weight: bold;
      border-radius: 5px;
      z-index: 5;

      @media(min-width: 426px) {
        width: 55%;
      }
    }

    .description {
      background: rgb(185, 185, 185);
      font-weight: bold;
      border-radius: 5px;
      z-index: 5;

      @media(min-width: 426px) {
        width: 55%;
      }
    }

    .resources {
      background: rgb(185, 185, 185);
      font-weight: bold;
      border-radius: 5px;
      z-index: 5;

      @media(min-width: 426px) {
        width: 55%;
      }
    }

    .description {
      height: 10em;
    }

    .buttonsContainer {
      display: flex;
      align-items: space-between;
      z-index: 5;

      .cancelButton {
        background-color: rgb(177, 5, 5);
        width: 8vw;
        border-radius: 2%;
        color: #f2f2f2;
        margin-right: 5vw;
        &:hover{
          background-color: rgb(143, 4, 4);
        }
        &:active{
          background-color: rgb(109, 4, 4);
        }
      }

      .sendButton {
        background-color: rgb(92, 0, 92);
        width: 8vw;
        border-radius: 2%;
        color: #f2f2f2;
        margin-left: 5vw;
        &:hover{
          background-color: rgb(70, 0, 70);
        }
        &:active{
          background-color: rgb(43, 0, 43);
        }
      }
    }
  }
}

@media(max-width: 426px) {
  form {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 100%;
    padding: 1em;
    height: 35em;
    background: #FF4700;

    .title {
      width: 100%;
      border: 2px solid grey;
      background: rgb(185, 185, 185);
      font-weight: bold;
    }

    .description {
      width: 100%;
      border: 2px solid grey;
      background: rgb(185, 185, 185);
      font-weight: bold;
    }

    .resources {
      width: 100%;
      border: 2px solid grey;
      background: rgb(185, 185, 185);
      font-weight: bold;
    }

    .addYourPubli {
      color: white;
      font-size: 4vh;
      font-weight: bold;
    }

    .description {
      height: 10em;
    }

    .buttonsContainer {
      width: 90vw;
      display: flex;
      justify-content: space-between;
      z-index: 2;

      .cancelButton {
        background-color: rgb(177, 5, 5);
        border-radius: 5px;
        color: #f2f2f2;
        &:hover{
          background-color: rgb(143, 4, 4);
        }
        &:active{
          background-color: rgb(109, 4, 4);
        }
      }

      .sendButton {
        background-color: rgb(92, 0, 92);
        border-radius: 5px;
        color: #f2f2f2;
        &:hover{
          background-color: rgb(70, 0, 70);
        }
        &:active{
          background-color: rgb(43, 0, 43);
        }
      }
    }
  }
}
</style>
