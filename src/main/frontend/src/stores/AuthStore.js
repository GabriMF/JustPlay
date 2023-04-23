import { ref, watch } from "vue";
import { defineStore } from "pinia";

export const useAuthStore = defineStore("auth", () => {
  const initialState = JSON.parse(localStorage.getItem("auth") || "{}");

  let isAuthenticate = ref(initialState.isAuthenticate || false);
  const username = ref(initialState.username || "");
  const roles = ref(initialState.roles || []);
  const id = ref(initialState.id || "");
  const name = ref(initialState.name || "Nombre");
  const instagram = ref(initialState.instagram || "Apellido");
  const location = ref(initialState.location || "");
  const spotify = ref(initialState.spotify || "");
  const youtube = ref(initialState.youtube || "");

  watch(
    () => ({
      isAuthenticate: isAuthenticate.value,
      username: username.value,
      roles: roles.value,
      id: id.value,
      name: name.value,
      instagram: instagram.value,
      location: location.value,
      spotify: spotify.value,
      youtube: youtube.value
    }),
    (state) => {
      localStorage.setItem("auth", JSON.stringify(state));
    }
  );

  const setRole = (role) => {
    if (!roles.value.includes(role)) {
      roles.value.push(role);
    }
  };

  const setUsername = (newUsername) => {
    username.value = newUsername;
  };

  const setIsAuthenticated = () => {
    isAuthenticate.value = true;
  };

  const setId = (newId) => {
    id.value = newId;
  };

  const setName = (newName) => {
    name.value = newName;
  };

  const setinstagram = (newinstagram) => {
    instagram.value = newinstagram;
  };

  const setLocation = (newLocation) => {
    location.value = newLocation;
  };

  const setspotify = (newspotify) => {
    spotify.value = newspotify;
  };

  const setyoutube = (newyoutube) => {
    youtube.value = newyoutube;
  };


  
  return { isAuthenticate, username, roles, id, name, instagram, location, spotify, youtube, 
    setRole, setUsername, setIsAuthenticated, setId, setName, setinstagram, setLocation, setspotify, setyoutube};
});