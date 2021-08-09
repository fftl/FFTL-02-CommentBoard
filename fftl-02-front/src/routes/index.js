import {createRouter, createWebHistory} from 'vue-router';
import List from '@/components/board/List'; //게시판 리스트 컴포넌트 호출
import Write from '@/components/board/Write'; //게시판 리스트 컴포넌트 호출
import View from '@/components/board/View'; //게시판 리스트 컴포넌트 호출
import Join from '@/components/user/Join';
import Login from '@/components/user/Login';
import Home from '@/components/home';

const routerHistory = createWebHistory()

const router = createRouter(({
    history: routerHistory,
    routes:[
		{
			path:'/'
			,component:Home
		}
		,{
			path:'/board/list'
			,component:List
		}
        ,{
			path:'/board/write'
			,component:Write
		},
        {
			path:'/board/view'
			,component:View
		},
		{
			path:'/user/join'
			,component:Join
		},
		{
			path:'/user/login'
			,component:Login
		}
	]
}))

export default router