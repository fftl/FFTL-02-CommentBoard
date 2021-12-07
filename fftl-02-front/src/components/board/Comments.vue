<template>
  <div id="comments">
    <p>
      <span>{{ nickname }} : </span
      ><input
        id="inputComment"
        type="text"
        v-model="comment"
        ref="comment"
      /><button @click="addComment">입력</button>
    </p>
    <table class="comments">
      <tr v-for="c in comments" :key="c.id">
        <td class="nickname">{{ c.nickname }}</td>
        <td class="comment">{{ c.comment }}</td>
        <td v-if="userCheck(c.user_id)">
          <a @click="deleteComment(c.id)" class="deleteComment">X</a>
        </td>
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
  data() {
    return {
      user_id: this.$store.state.user_id,
      nickname: "",
      comment: "",
      board_id: this.$route.query.board_id,
      form: "",
    };
  },

  mounted() {
    this.nickname = this.$store.state.nickname;
  },

  props: {
    commentsArray: Array,
  },

  methods: {
    userCheck(user_id) {
      if (user_id == this.user_id) {
        return true;
      }
    },
    addComment() {
      this.form = {
        nickname: this.nickname,
        comment: this.comment,
        regdate: cregdate,
        user_id: this.$store.state.user_id,
        board_id: this.$route.query.board_id,
      };

      this.$http
        .post("https://fftl-02-back.herokuapp.com/comment/", this.form, {
          headers: { Authorization: this.$store.state.token },
        })
        .then((res) => {
          if (res.data.success) {
            alert("댓글이 등록되었습니다.");
            window.location.reload();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    deleteComment(id) {
      if (confirm("정말 삭제하시겠습니까?") == true) {
        this.$http
          .delete("https://fftl-02-back.herokuapp.com/comment/" + id, {
            headers: { Authorization: this.$store.state.token },
          })
          .then((res) => {
            if (res.data.success) {
              alert("삭제되었습니다.");
              window.location.reload();
            } else {
              alert("실행중 실패했습니다.\n다시 이용해 주세요");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        return;
      }
    },
  },

  // 종속 대상이 변경될 때마다 함수를 실행합니다.
  // 변경되지 않을 경우에는 실행되지 않습니다.
  computed: {
    comments() {
      return this.commentsArray;
    },
  },
};
</script>
<style scoped>
#inputComment {
  width: 80%;
}
.comments {
  position: relative;
  border: 1px solid;
  margin-bottom: 100px;
}
.comments .nickname {
  font: bold;
}
.deleteComment {
  font-size: 12px;
  padding: 5px;
  background: red;
  color: #fff;
  cursor: pointer;
}
</style>>
