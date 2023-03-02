var sender;
var recipient;
var message;

// Função para enviar uma mensagem para a API
function sendMessage(sender, recipient, message) {

  fetch('http://localhost:8080/api/chat/message?sender=' + sender + '&recipient=' + recipient + '&message=' + message,{
    method: "POST"
  })

    .then(response => response.json())
    .then(data => console.log(data))
}

function getPreviousMessages() {
  // fazer requisição GET à API para recuperar mensagens anteriores
  fetch('http://localhost:8080/api/chat/messages?sender=' + sender + '&recipient=' + recipient)
    .then(response => response.json())
    .then(messages => {
      // exibir as mensagens no chat
      messages.forEach(message => {
        displayMessage(message);
      });
    });
}

function displayMessage(message) {
  // verificar se a mensagem já foi exibida no chat
  if (document.getElementById(message.id)) {
    return;
  }
  // exibir a mensagem no chat
  const chat = document.getElementById('chat');
  const messageElement = document.createElement('div');
  messageElement.id = message.id;
  messageElement.classList.add('message', message.sender === sender ? 'sent' : 'received');
  messageElement.innerHTML = message.text;
  chat.appendChild(messageElement);
}

window.addEventListener('load', () => {
  getPreviousMessages();
});