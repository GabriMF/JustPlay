import axios from "axios";
import { ref } from "vue";

export default class UserService {
    user;
    #url = "http://localhost:8080/api/users"

    constructor() {
        this.user = ref([]);
    }
    
    getUser(){
        return this.user.value
    }

async fetchAllUsers(){
    try {
        await axios({
            method: "GET",
            url: this.#url,
            withCredentials: true,
        }).then(response =>{
            this.user.value=response.data
            console.log(response.data)
           
        });
        
    } catch (error) {
        console.log(error)
    }
}
}


   

    
   

