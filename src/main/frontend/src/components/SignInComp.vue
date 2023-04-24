<script setup>
import { ref, reactive } from "vue";
import AuthService from "../services/AuthService";
import { useRouter } from "vue-router";

const router = useRouter();

const email = ref(""),
    emailRules = reactive([
        (v) => !!v || "Es necesario introducir un e-mail",
        (v) => /.+@.+/.test(v) || "El e-mail debe ser en el formato correcto",
    ]),
    password = ref(""),
    passwordRules = reactive([
        (v) => !!v || "Es necesario introducir una contraseña",
        (v) => v.length >= 8 || "La contraseña debe tener al menos 8 caracteres",
    ]),
    confirmPassword = ref(""),
    confirmPasswordRules = reactive([
        (v) => !!v || "Confirme su contraseña",
        (v) => v === password.value || "Las contraseñas no coinciden",
    ]);

const submitData = async () => {
    const authService = new AuthService();
    if (email.value && password.value != null) {
        try {
            const response = await authService.register(email.value, password.value);
            alert("Registrado con éxito");
            router.push("/");
        } catch (error) {
            console.error(error);
        }
    } else {
        alert("Debes rellenar todos los campos");
    }
};
</script>

<template>
    <div class="signIn">
        <h1 class="headerForm">Bienvenido a SomosF5</h1>
        <v-sheet class="mx-auto">
            <v-form v-model="valid" @submit.prevent="submitData">
                <v-text-field v-model="email" :rules="emailRules" label="Correo Electrónico" required></v-text-field>

                <v-text-field v-model="password" :rules="passwordRules" :type="show1 ? 'text' : 'password'"
                    name="input-10-1" label="Contraseña" required>
                </v-text-field>

                <v-text-field v-model="confirmPassword" :rules="confirmPasswordRules" :type="show1 ? 'text' : 'password'"
                    name="input-10-1" label="Repetir contraseña" required>
                </v-text-field>
                <div class="d-flex flex-column">
                    <v-btn type="submit" class="mt-4" block @click="validate">
                        Registrarse
                    </v-btn>
                </div>
            </v-form>
        </v-sheet>
    </div>
</template>

<style lang="scss">
@use "@/scss/fonts";

.signIn {
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
        margin-top: 1%;
        background-color: rgb(87, 87, 87);
        border-radius: 5px;
        padding: 1vw;

        .v-form {
            display: flex;
            flex-direction: column;

            .v-input__control {
                margin-top: 4vh;
                background-color: rgb(190, 190, 190);
                border-radius: 5px;
                z-index: 2;
            }

            .v-input__details {
                z-index: 5;

                .v-messages__message {
                    color: #FED2C0;
                }
            }

            .passwordLink {
                color: white;
                display: flex;
                justify-content: center;
                z-index: 2;
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
                    width: 27vw;

                    .v-btn__content {
                        font-size: 2vh;
                    }
                }
            }
        }
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
                height: 70vh;
                width: 50vw;

                .v-btn--size-default {
                    height: 7vh;
                    width: 27vw;

                    .v-btn__content {
                        font-size: 10vh;
                    }
                }
            }
        }
    }
}
</style>
