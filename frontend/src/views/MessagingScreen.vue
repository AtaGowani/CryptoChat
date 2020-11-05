<template lang="pug">
  .chatbox#app(
    @click=''
  )
    .chatbox__container
      .chatbox__info
        p {{ contacts[selectedContactIndex].name }}
      .chatbox__chat
        input.chatbox__messageInput(
          v-model="selectedContact.messageInput"
          ref="newMessageInput"
          :placeholder="firstMessageSent ? 'New message...':'New message... (press enter to send)'"
          @keyup.enter="newMessage()"
        )
        .chatbox__messages
          template(v-for="(message, messageIndex) in selectedContact.messages")
            .chatbox__date(
              v-if="messageIndex === 0 || messageIndex > 0 && selectedContact.messages[messageIndex - 1].date !== message.date"
            ) {{message.date}}
            .chatbox__messageContainer(
              :class="message.authorId === userId ? 'chatbox__messageContainer--right':''"
              :style=`{
                'margin-top': messageIndex > 0 && selectedContact.messages[messageIndex - 1].authorId !== message.authorId ? '12px':'4px',
                'margin-bottom': messageIndex < (selectedContact.messages.length - 1) && selectedContact.messages[messageIndex + 1].authorId !== message.authorId ? '12px':'4px'
              }`
            )
              .chatbox__message(
                aria-label="message.content"
                :style=`{
                  'background-color': message.authorId === userId ? 'background-color':'lightgrey',
                  'border-top-right-radius': (messageIndex > 0 && selectedContact.messages[messageIndex - 1].authorId === userId && message.authorId === userId) ? '5px':'20px',
                  'border-bottom-right-radius': (messageIndex < (selectedContact.messages.length - 1) && selectedContact.messages[messageIndex + 1].authorId === userId && message.authorId === userId) ? '5px':'20px',
                  'border-top-left-radius': (messageIndex > 0 && selectedContact.messages[messageIndex - 1].authorId === message.authorId && message.authorId !== userId) ? '5px':'20px',
                  'border-bottom-left-radius': (messageIndex < (selectedContact.messages.length - 1) && selectedContact.messages[messageIndex + 1].authorId === message.authorId && message.authorId !== userId) ? '5px':'20px'
                }`
              ) {{ message.content }}
                .chatbox__time(:class="message.authorId === userId ? 'chatbox__time--left':''") {{message.time}}
</template>

<script>
function makeId(length) {
  let result = "";
  let characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  let charactersLength = characters.length;
  for (let i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }
  return result;
}
export default {
  computed: {
    selectedContact() {
      return this.contacts[this.selectedContactIndex];
    }
  },
  methods: {
    newMessage() {
      if (this.selectedContact.messageInput.length > 0) {
        //let today = new Date();
        this.selectedContact.messages.push({
          content: this.selectedContact.messageInput,
          authorId: this.userId,
          time: this.getTime(),
          date: this.getDate()
        });
        this.firstMessageSent = true;
        this.selectedContact.messageInput = "";
      }
    },
    getDate() {
      let date = new Date();
      return (
          date.getFullYear() +
          "-" +
          (date.getMonth() + 1 < 10 ? "0" : "") +
          (date.getMonth() + 1) +
          "-" +
          (date.getDate() < 10 ? "0" : "") +
          date.getDate()
      );
    },
    getTime() {
      let date = new Date();
      let hours = date.getHours();
      let minutes = date.getMinutes();
      let ampm = hours <= 12 ? "am" : "pm";
      hours = hours % 12;
      hours = hours ? hours : 12;
      minutes = minutes < 10 ? "0" + minutes : minutes;
      return hours + ":" + minutes + " " + ampm;
    }
  },
  data: () =>({
    userId: makeId(8),
    firstMessageSent: false,
    selectedContactIndex: 0,
    contacts: [
      {
        name: "Adam Bush",
        userId: "umYHX3R",
        messageInput: "",
        messages: [
          {
            content: "Hi, how are you?",
            date: "2020-11-04",
            time: "12:34 pm",
            authorId: "umYHX3R"
          }
        ]
      }
    ]
  })
}
</script>

<style lang="stylus">

@import url("https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700")
a:focus, a:active,
button::-moz-focus-inner,
input[type="reset"]::-moz-focus-inner,
input[type="button"]::-moz-focus-inner,
input[type="submit"]::-moz-focus-inner,
select::-moz-focus-inner,
input[type="file"] > input[type="button"]::-moz-focus-inner {
  border: 0
  outline: 0
}

*
  box-sizing border-box
  text-align center
*:focus
  outline none
p
  margin 0
html,body
  margin 0
  font-size 13px
  height 100%
  font-family 'Open Sans', sans-serif
  overflow hidden
footer
  width 100%

  font-size 10px
  color #bbb
  a
    text-decoration none
    color #999
.chatbox
  display flex
  align-items center
  justify-content flex-start
  width 100%
  height 100%
  &__container
    position relative
    width calc(100% - 175px)
    height 100%
  &__info
    border 1px hidden
    background lightgrey
    font-size 20px
    padding 10px 10px
    align-items center
    display flex
    text-transform capitalize
  &__chat
    position relative
    height calc(100% - 78px)
  &__messages
    margin 0 auto
    height 100%
    padding 20px
    padding-bottom 75px
    overflow-y auto
  &__messageInput
    position absolute
    display block
    font inherit
    font-size 13px
    text-align left
    margin 0 auto
    right 0
    left 0
    height 37px
    padding 2px 19px
    width 450px
    border-radius 50px
    border 1px solid #e4e4e4
    bottom 26px
    z-index 2
    &::placeholder
      color #bbb
  &__messageContainer
    width 100%
    display flex
    align-items center
    margin 6px 0
    justify-content flex-start
    &--right
      justify-content flex-end
      .chatbox__message
        background-color yellow
      color black
      border none
  &__message
    position relative
    max-width 400px
    padding 6px 15px
    text-align left
    border-radius 20px
    font-size 12px
    color black
    word-break break-word
    background-color #f4f4f4
    &:hover
      .chatbox__time
        display block
  &__time
    display none
    position absolute
    color #ccc
    right -55px
    top calc(50% - 9px)
    &--left
      left -55px
      right auto
  &__date
    width 100%
    height 20px
    text-align center
    margin 13px 0 10px
    font-size 12px
    color #ccc
    &:nth-child(1)
      margin-top 0
  &__color
    width 25px
    height 25px
    border-radius 30px
    cursor pointer
    &:hover
      opacity .9
@media screen and (max-width: 600px)
  .chatbox__contacts
    width 85px
  .chatbox__container
    width calc(100% - 85px)
@media screen and (max-width: 640px)
  .chatbox__messageInput
    width 290px
</style>