<template>
	<div>
		<h2>게시판 리스트</h2>
        <button class="goWrite" @click="goWrite">글 작성하기</button>
        <paginated-list :list-array="pageArray" />
	</div>
</template>

<script>
import PaginatedList from './PaginatedList'
export default {
    components: {
        PaginatedList
    },
    data(){
        return {
            pageArray: []
        }
    },
    mounted(){
        this.getList()
    }
    ,methods: {
        getList(){
            this.$http.get("http://127.0.0.1:3000/board")
            .then((res)=>{
				console.log(res.data);
                this.pageArray = res.data;
			})
			.then((err)=>{
				console.log(err);
			})
        }
        ,goWrite(){
			this.$router.push({path:'/board/write'}); //추가한 상세페이지 라우터
        }
    }
}
</script>

<style scoped>
.goWrite{
      float: right;
}
</style>