<template>
<div>
    <p><span>{{ nickname }} : </span><input id="inputComment" type="text" v-model="comment" ref="comment" /><button @click="addComment">입력</button></p>
    <table class="comments">
        <tr>
            <td>nickname</td>
            <td>comment</td>
            <td>cregdate</td>
        </tr>
    </table>
</div>
</template>
<script>
export default {
    data(){
        return{
            nickname:""
        }
    },
    mounted(){
        this.nickname = this.$store.state.nickname;
    },
    methods:{
        addComment(){
            this.$http
            .post("http://localhost:3000/comment", this.form, { header : {'Authorization':'Bearer '+this.$store.state.token }})
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            })
        }
    } 
}
</script>
<style scoped>
.comments {
}

#inputComment {
    width: 80%;
}
</style>>
