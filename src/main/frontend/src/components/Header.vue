<script setup>
import AuthService from '../services/AuthService.js';
const service = new AuthService();

const logout = async () => {
  await service.logout()
}

</script>
<template>
  <div class="header">
    <a href="/" aria-current="page" class="logo">
      <img class="logoImg" src="../assets/images/justPlayLogo.png" alt="">
    </a>

    <div class="menuContainer">
      <nav role="navigation" class="menuNav">
        <router-link to="/" class="nav-bar__link" href="">
          Home
        </router-link>
        <router-link to="/myEvents" class="nav-bar__link" href="">
          My Events
        </router-link>
        <router-link to="/favoriteBands" class="nav-bar__link" href="">
          Favorite Bands
        </router-link>
        <router-link @click="logout" to="/login" href="">
          Log Out
        </router-link>
      </nav>

      <nav class="menuMobile">
        <input type="checkbox" id="menu" />
        <label for="menu"> ☰ </label>
        <ul>
          <router-link to="/ProfileUser" class="nav-bar__link" href="">Mis Publicaciones</router-link>
          <router-link to="/MisContactos" class="nav-bar__link" href="">Mis Contactos</router-link>
          <router-link to="/ElMuro" class="navLink" href="">El Muro</router-link>
          <router-link @click="logout" to="/" href="">LogOut</router-link>
        </ul>
      </nav>
    </div>
  </div>
</template>
<style lang="scss" scoped>
@use "@/scss/colors" as c;
@use "@/scss/mixins" as m;
@use "@/scss/fonts";

.header {
  width: 100vw;
  position: sticky;
  z-index: 10;
  left: 0%;
  top: 0%;
  right: 0%;
  bottom: 0%;
  display: flex;
  height: 5rem;
  -webkit-box-pack: center;
  justify-content: space-between;
  background-color: rgb(177, 5, 5);
  margin-bottom: 1px;

  .logo {
    display: flex;
    align-items: center;
    margin-left: 1.5vw;
    .logoImg {
      height: 12vh;
    }
  }

  .menuContainer {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;

    .menuNav {
      display: flex;
      -webkit-box-align: center;
      align-items: center;
      grid-column-gap: 2rem;
      font-family: "openSans";
      margin-right: 1em;


      @include m.mv(500px) {
        display: none;
      }
    }

    .menuMobile {
      display: none;

      @include m.mv(500px) {
        margin: auto;
        margin-right: 1vw;
        display: block;

        ul {
          display: none;
        }

        input:checked~ul {
          display: block;
        }

        input {
          display: none;
        }

        label {
          box-sizing: border-box;
          display: inline-block;
          background: map-get(c.$colors, "light-orange");
          border-radius: 5px;
          width: 30px;
          height: 30px;
          line-height: 30px;
          font-weight: bold;
          text-align: center;
          user-select: none;
        }

        /* Estilo del boton cuando se pasa el mouse por encima*/
        // label:hover {
        //   background: map-get(c.$colors, "light-orange");
        // }

        /* Estilo del boton cuando el menú está expandido*/
        // input:checked ~ label {
        //   background: map-get(c.$colors, "light-orange");
        // }

        ul {
          margin-top: 0;
          padding: 0;
          width: 200px;
          font-family: 'open Sans', 'Sans serif';
        }

        li {
          display: block;
          // background: map-get(c.$colors, "light-orange");
          margin: 0;
          padding: 10px;
          list-style: none;
          border-bottom: 1px solid grey;
        }

        /*Estilo cuando el mouse pasa encima de cada link del menu*/
        li:hover {
          filter: brightness(110%);
        }
      }
    }
  }
}
</style>
