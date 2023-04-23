<script>
export default {
    data() {
        return {
            
            pics: [
                'indigo',
                'warning',
                'pink darken-2',
                'red lighten-1',
                'deep-purple accent-4',
            ],
            slides: [
                'First',
                'Second',
                'Third',
                'Fourth',
                'Fifth',
            ],
        }
    },
}
</script>
<template>
    <v-carousel class="carousel" cycle height="40vh" width="80vw" hide-delimiter-background show-arrows="hover">
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
            <v-sheet :color="pics[i]" height="100%" width="100%">
                <div class="d-flex fill-height justify-center align-center">
                    <div class="text-h2">
                        {{ slide }} Slide
                    </div>
                </div>
            </v-sheet>
        </v-carousel-item>
    </v-carousel>
</template>
<style>
.carousel{
    margin-top: 2vh;
    margin-bottom: 2vh;
}
</style>

