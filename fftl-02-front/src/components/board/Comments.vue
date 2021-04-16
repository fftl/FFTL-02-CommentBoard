<template>
<div>
    <p><span>{{ nickname }} : </span><input id="inputComment" type="text" v-model="comment" ref="comment" />{{ comment }}<button @click="addComment">입력</button></p>
    <table class="comments">
        <tr v-for="c in comments" :key="c.cid">
            <td>{{ c.nickname }} </td>
            <td>{{ c.comment }} </td>
            <td>{{ c.cregdate }} </td>
        </tr>
    </table>
</div>
</template>
<script>
let today = new Date();
let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1; // 월
let date = today.getDate(); // 날짜

let cregdate = year + "-" + month + "-" + date;
export default {
    data(){
        return{
            nickname:"",
            comment:"",
            form:""
        }
    },
    mounted(){
        this.nickname = this.$store.state.nickname;
    },
    props: {
        commentsArray: Array,
    },
    methods:{
        addComment(){
            this.form = {
                nickname: this.nickname,
                comment: this.comment,
                cregdate: cregdate,
                uid: this.$store.state.uid,
                bid: this.$route.query.bid,
            }
            console.log(this.form);
            this.$http
            .post("http://localhost:3000/comment/", this.form, { headers : {'Authorization':'Bearer '+ this.$store.state.token }})
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            })
        }
    },
    // 종속 대상이 변경될 때마다 함수를 실행합니다.
    // 변경되지 않을 경우에는 실행되지 않습니다.
    computed:{
        comments(){
            return this.commentsArray;
        }
    }
}
</script>
<style scoped>

#inputComment {
    width: 80%;
}
</style>>
