<script setup>
import { ref, reactive } from "vue";

import { RouterLink, useRouter } from "vue-router";
import AuthService from "../services/AuthService";
import { useAuthStore } from "../stores/AuthStore";
const auth = useAuthStore();
const router = useRouter();

const email = ref(""),

  emailRules = reactive([
    (v) => !!v || "Es necesario introducir un e-mail",
    (v) => /.+@.+/.test(v) || "Introduzca un e-mail válido",
  ]),
  password = ref(""),
  passwordRules = reactive([
    (v) => !!v || "Es necesario introducir una contraseña",
    (v) => v.length >= 8 || "La contraseña debe tener al menos 8 caracteres",

  ]);

const submitData = async () => {
  const authService = new AuthService();
  try {
    const user = await authService.login(email.value, password.value);
    auth.setRole(user.role);
    auth.setUsername(user.email);
    auth.setId(user.id);
    auth.setName(user.name);
    auth.setinstagram(user.instagram);
    auth.setLocation(user.location);
    auth.setspotify(user.spotify);
    auth.setyoutube(user.youtube);
    auth.setIsAuthenticated();

    if (user.role == "ROLE_ADMIN") {
      router.push("/MuroAdmin/")
    }
    else {
      router.push("/home");
    }
    console.log(user);
  } catch (error) {
    console.error(error);
    alert("No te conozco")
  }
  onReset();
};
</script>

<template>
  <div class="logIn">
    <h1 class="headerForm">Welcome to Just Play!</h1>
    <h2 class="headerForm2">Log in to see some shows dates, new from your groups or to post some by yourself!</h2>
    <v-sheet class="mx-auto">
      <v-form id="loginForm" @submit.prevent="submitData">

        <v-text-field v-model="email" :rules="emailRules" label="Correo electrónico" required>
        </v-text-field>

        <v-text-field v-model="password" :rules="passwordRules" :type="show1 ? 'text' : 'password'" name="input-10-1"
          label="Contraseña" required>
        </v-text-field>

        <!-- <a class="passwordLink" href="">¿Has olvidado tu contraseña?</a> -->

        <div class="d-flex flex-column">
          <v-btn type="submit" class="mt-4" block @click="validate">
            Enviar
          </v-btn>
        </div>
      </v-form>
    </v-sheet>
    <div class="noAccountLink">
      <a href="/signin">
        No account? Don't worry! Just click here and sign up
      </a>
    </div>
  </div>
</template>

<style lang="scss">
@use "@/scss/fonts";

.logIn {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 2vh;

  .headerForm {
    background-size: cover;
    background-repeat: no-repeat;
    color: white;
    font-family: 'openSans';
    font-weight: bold;
    font-size: 6vh;
    align-self: center;
    margin-bottom: 1vh;
  }

  .headerForm2 {
    color: white;
    font-family: 'openSans';
    font-weight: bold;
    font-size: 3vh;
    align-self: center;
    margin-bottom: 1vh;
  }

  @media(max-width: 599px) {
    .headerForm {
      display: flex;
      justify-content: center;
      align-items: center;
      color: white;
      font-family: 'openSans';
      font-weight: bold;
      font-size: 4vh;
    }
  }

  .v-sheet {
    width: 35vw;
    background-color: rgb(87, 87, 87);
    border-radius: 5px;
    padding: 1vw;

    .v-form {

      .v-input__control {
        margin-top: 4vh;
        background-color: rgb(190, 190, 190);
        border-radius: 5px;
      }

      .passwordLink {
        color: white;
        display: flex;
        justify-content: center;
      }

      .d-flex.flex-column {
        width: fit-content;
        margin: auto;

        .v-btn {
          color: black;
          background-color: rgb(177, 5, 5);
          font-weight: bolder;
          margin-bottom: 5vh;
        }

        .v-btn--size-default {
          min-width: 25vw;
        }
      }
    }
  }

  .noAccountLink {
    color: white;
    display: flex;
    justify-content: center;
  }

  @media(max-width: 599px) {

    .v-sheet {
      width: 70vw;
      margin-top: 1%;

      .v-form {
        display: flex;
        flex-direction: column;
        margin: auto;
        height: 50%;
        width: 70vw;
      }
    }
  }

  @media(min-width: 1440px) {

    .v-sheet {
      width: 50vw;
      margin-top: 1%;

      .v-form {
        display: flex;
        flex-direction: column;
        margin: auto;
        height: 50vh;
        width: 50vw;

        .v-field__field {
          height: 10vh;
          font-size: 3vh;
        }

        .v-input__details {
          margin-bottom: 7vh;

          .v-messages__message {
            color: white;
            font-size: 2vh;
          }
        }

        .v-btn--size-default {
          height: 6vh;
          width: 27vw;

          .v-btn__content {
            font-size: 5vh;
          }
        }
      }
    }
  }
}
</style>
