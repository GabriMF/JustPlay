<script setup>
import { ref, reactive, computed, onBeforeMount, onBeforeUpdate } from "vue";
import axios from "axios";
import { useAuthStore } from "../stores/AuthStore";

const store = useAuthStore();
const url = ref("");
const user = ref("");
const profile = ref("");
const nameModel = ref(store.name);
const instagramModel = ref(store.instagram);
const spotifyModel = ref(store.spotify);
const youtubeModel = ref(store.youtube);
const locationModel = ref(store.location);
const imageUrl = computed(() => url.value);
let readOnly = ref(true);

const onFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const formData = new FormData();
    formData.append("file", file);
    axios({
      method: "POST",
      url: "http://localhost:8080/media/upload",
      data: formData,
      withCredentials: true,
    })
      .then((response) => {
        url.value = response.data.url;
      })
      .catch((e) => {
        console.log(e);
        console.log("Catch error upload");
      });
  }
  window.location.reload();
};

onBeforeMount(() => {
  axios({
    method: "GET",
    url: "http://localhost:8080/api/users/" + store.id,
    withCredentials: true,
  })
    .then((response) => {
      user.value = response.data;
    })
    .catch((e) => {
      console.log(e);
      console.log(store.id);
      console.log("Catch error profile");
    });
});

const submit = async () => {
  console.log("soy submit");
  readOnly.value = true;
  console.log(readOnly.value);
  try {
    store.name = nameModel.value,
      store.instagram = instagramModel.value,
      store.spotify = spotifyModel.value,
      store.youtube = youtubeModel.value,
      store.location = locationModel.value
    await axios({
      method: "PUT",
      url: "http://localhost:8080/api/profiles/update/" + store.id,
      data: store,
      withCredentials: true,
    });
  } catch (error) {
    console.log("submit error" + error);
  }
};


onBeforeUpdate(() => {
  axios({
    method: "GET",
    url: "http://localhost:8080/api/profiles/" + store.id,
    withCredentials: true,
  })
    .then((response) => {
      store.value = response.data;
    })
    .catch((e) => {
      console.log(e);
      console.log("Catch error profile");
    });
});
</script>

<template>
  <div class="infoUser">
    <div class="nameAndContact">
      <div class="nameAndButton">

        <div class="contactName">
          <div class="name">
            <h1 class="text">{{ nameModel }}</h1>
            <input v-model="nameModel" v-if="modelvalue != null" modelvalue="name"
              @update:modelValue="newValue => name = newValue" class="name" :readonly="readOnly" />

            <input v-model="nameModel" v-else placeholder="Nombre" modelvalue="name"
              @update:modelValue="newValue => name = newValue" class="name" :readonly="readOnly" />
          </div>
        </div>

        <button v-if="readOnly == true" @click="readOnly = false" class="buttonEdit">
          EDITAR
        </button>
        <button v-if="readOnly == false" @click="submit" class="buttonSave">
          GUARDAR
        </button>
      </div>
      <div class="contacts">

        <div class="contact">
          <a class="contactUrl" target="_blank" :href="'https://instagram.com/' + store.instagram">
            <img class="logo" src="../assets/images/icons/instagramIcon.png" alt="instagram" />
          </a>
          <input v-model="instagramModel" v-if="modelvalue != null" modelvalue="instagram"
            @update:modelValue="newValue => instagram = newValue" class="contactsName" :readonly="readOnly" />
          <input v-model="instagramModel" v-else placeholder="instagram" modelvalue="instagram"
            @update:modelValue="newValue => instagram = newValue" class="contactsName" :readonly="readOnly" />
        </div>


        <div class="contact">
          <a class="contactUrl" target="_blank" :href="'https://spotify.com/' + store.spotify">
            <img class="logo" src="../assets/images/icons/spotifyIcon.png" alt="spotify" />
          </a>
          <input v-model="spotifyModel" v-if="modelvalue != null" modelvalue="spotify"
            @update:modelValue="newValue => spotify = newValue" class="contactsName" :readonly="readOnly" />
          <input v-model="spotifyModel" v-else placeholder="spotify" modelvalue="spotify"
            @update:modelValue="newValue => spotify = newValue" class="contactsName" :readonly="readOnly" />
        </div>
        <div class="contact">
          <a class="contactUrl" target="_blank" :href="'https://www.youtube.com/in/' + store.youtube">
            <img class="logo" src="../assets/images/icons/youtubeIconBlack.png" alt="youtube" />
          </a>
          <input v-model="youtubeModel" v-if="modelvalue != null" modelvalue="youtube"
            @update:modelValue="newValue => youtube = newValue" class="contactsName" :readonly="readOnly" />
          <input v-model="youtubeModel" v-else placeholder="youtube" modelvalue="youtube"
            @update:modelValue="newValue => youtube = newValue" class="contactsName" :readonly="readOnly" />
        </div>
        <div class="contact">
          <div class="contactUrl">
            <img class="logo" src="../assets/images/icons/locationIcon.png" alt="location" />
          </div>
          <input v-model="locationModel" v-if="modelvalue != null" modelvalue="location"
            @update:modelValue="newValue => location = newValue" class="contactsName" :readonly="readOnly" />
          <input v-model="locationModel" v-else placeholder="Location" modelvalue="location"
            @update:modelValue="newValue => location = newValue" class="contactsName" :readonly="readOnly" />
        </div>
        <div class="contact">
          <div class="contactUrl">
            <img class="logo" src="../assets/images/icons/emailIcon.png" alt="email" />
          </div>
          <input :value="user.username" class="contactsName" :readonly="true" />
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use "@/scss/fonts";

.infoUser {
  width: 100%;
  background-color: rgb(180, 10, 10);
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  margin-top: 1vh;
  border-radius: 5px;


  .nameAndContact {
    display: flex;
    flex-direction: column;
    width: 80%;
    align-items: center;
    justify-items: right;
    margin-left: 1%;

    .nameAndButton {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;

      .contactName {
        display: flex;
        flex-direction: row;
        align-items: center;
        font-family: "openSans";
        margin-bottom: 0.5em;

        .name {
          width: fit-content;
          display: inline-block;

        }
        .name {
          width: fit-content;
          display: inline-block;

          .text {
            color: white;
            font-size: xx-large;
            font-weight: bold;
          }

          .name {
            &:read-only {
              display: inline-block;
              width: fit-content;
              font-size: 1.8em;
              color: white;
              font-family: "openSans";
              font-weight: bold;
              margin-left: 1%;
              display: none;
            }

            &:read-write {
              background-color: #FEF0DC;
              border-radius: 5px;
              padding: 1vh;
              color: black;
              margin-bottom: 2%;
            }
          }
        }
      }

      .buttonEdit {
        display: flex;
        width: 7vw;
        height: 3vh;
        align-items: center;
        border: solid;
        box-sizing: border-box;
        border-radius: 50px;
        border-color: white;
        color: #FF4700;
        background-color: white;
        font-family: "openSans";
        font-weight: bold;
        font-size: 70%;
        justify-content: center;
        text-align: center;

        &:hover {
          background-color: #FF4700;
          color: white;
        }
      }

      .buttonSave {
        display: flex;
        width: 8vw;
        height: 3vh;
        align-items: center;
        border: solid;
        box-sizing: border-box;
        border-radius: 50px;
        color: white;
        background-color: rgb(102, 102, 102);
        font-family: "openSans";
        font-weight: bold;
        font-size: 70%;
        justify-content: center;
        text-align: center;

        &:hover {
          background-color: white;
          color: black;
        }
      }
    }

    .contacts {
      width: 100%;
      display: flex;
      justify-content: space-between;

      .contact {
        display: flex;
        flex-direction: row;
        align-items: center;
        font-family: "openSans";
        margin-bottom: 0.5em;

        .name {
          width: fit-content;
          display: inline-block;

          .text {
            color: white;
            font-size: xx-large;
            font-weight: bold;
          }

          .name {
            &:read-only {
              display: inline-block;
              width: fit-content;
              font-size: 1.8em;
              color: white;
              font-family: "openSans";
              font-weight: bold;
              margin-left: 1%;
              display: none;
            }

            &:read-write {
              background-color: #FEF0DC;
              border-radius: 5px;
              color: black;
              margin-bottom: 2vh;
            }
          }
        }

        .contactUrl {
          display: flex;
          flex-direction: row;
        }

        .logo {
          margin-right: 2vw;
          width: 3vw;
        }

        .contactsName {
          color: white;
          font-size: 1em;
        }

        input {
          width: 100%;
          color: white;
          border: 0;
          outline: none;

          &:read-write {
            background-color: #FEF0DC;
            color: black;
            padding-left: 1%;
            border-radius: 5px;
          }
        }
      }
    }
  }
}
</style>
