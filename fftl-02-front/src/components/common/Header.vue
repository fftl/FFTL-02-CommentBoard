<template>
	<header>
		<!-- 로그인이 되어있다면 loginOk 를 판단하여 로고 버튼이 list로 이동됩니다. -->
		<!-- 아니라면 로그인 화면으로 이동되게 됩니다. -->
		<h1 v-if="loginOk"><router-link to="/board/list"><img alt="Vue logo" src="../../assets/fftl-logo_basic.png" width="100"></router-link></h1>
		<h1 v-else><router-link to="/"><img alt="Vue logo" src="../../assets/fftl-logo_basic.png" width="100"></router-link></h1>
		<div class="menuWrap">
			<ul class="menu">
				<li v-if="loginOk"><router-link to="#" @click="logout">로그아웃</router-link></li>
			</ul>
		</div>
	</header>
</template>

<script>
export default {
	data(){
		return {
			loginOk: this.$store.state.loginCheck,
		}
	},
	mounted(){
	},
	//this.$store.state.loginCheck 의 값이 변경되는 것을 확인합니다.
	//watch에서 변경을 확인하였을 경우 data()의 loginOk값에 바로 할당해 줍니다.
	computed:{
		check_loginCheck(){
			return this.$store.state.loginCheck;
		}
	},
	watch:{
		check_loginCheck(val){
			this.loginOk = val;
		}
	},
	//******************************************************* */
	methods:{
		logout(){
			this.$store.commit('resetState');
			this.$router.push({ path: "/"});
			this.loginOk = false;
			alert("로그아웃 되었습니다.");
		}
	}
}
</script>

<style scoped>
header{width:100%; text-align:center; position:relative; height:120px; border-bottom:1px solid #35495e}
header h1{position:absolute; top:0; left:100px;}
header ul.menu:after{display:block; clear:both; content:'';}
header ul.menu{position:absolute; top:20px; right:50px;}
header ul.menu li{float:left; padding:10px 20px; list-style:none;}

a{text-decoration:none; color:#333;}
</style>