
const LanguageData = {

    English: {
        greeting: "Welcome to the Better Version of Blooket!!!",
        description: "Answer questions to earn gold, then pick a bonus!------------------------------------",
        
    },


    Spanish: {
        greeting: "¡¡¡Bienvenido a la mejor versión de Blooket!!!",
        description: "Responde preguntas para ganar oro y ¡luego elige un bono!------------------------------------",
    },


    German: {
        greeting: "Willkommen zur besseren Version von Blooket!!!",
        description: "Beantworten Sie Fragen, um Gold zu verdienen, und wählen Sie dann einen Bonus aus!------------------------------------",
    },


    French: {
        greeting: "Bienvenue dans la meilleure version de Blooket !!!r",
        description: "Répondez aux questions pour gagner de l'or, puis choisissez un bonus !------------------------------",
    },
};


const langLinks = document.querySelectorAll('.lang-menu ul li a');



langLinks.forEach(link =>{
    link.addEventListener('click', (e) => {
        e.preventDefault();
        console.log('Clicked language:', e.target.className)
        const languageCode = e.target.className;
        changeLanguage(languageCode);
    });
    
});

const music = document.getElementById('music');
const volumeSlider = document.querySelector('.volumeSlider');

volumeSlider.addEventListener('input', function() {
    music.volume = this.value;
});

document.body.addEventListener('click', function() {
    music.muted = false;
    music.play();
});
 

const questions = [
    {
        text: "What is the capital of France?",
        choices: ["Paris", "Berlin", "Madrid", "Rome"],
        answer: "Paris"
    },

    {
        text: "Which planet is known as the 'Red Planet'?",
        choices:["Mars", "Earth", "Jupiter", "Venus"],
        answer: "Mars"
    },

    {
        text: "What is 7 + 8?",
        choices: ["15", "12", "16", "14"],
        answer: "15"
    },

    {
        text: "Who painted the Mona Lisa?",
        choices: ["Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"],
        answer: "Leonardo da Vinci"
    },

    {
        text: "What is the largest ocean on Earth?",
        choices: ["Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"],
        answer: "Pacific Ocean"
    },

    {
        text: "What is the chemical symbol for water?",
        choices: ["H2O", "CO2", "O2", "N2"],
        answer: "H2O"
    },

    {   text: "What is the fastest land animal?",
        choices: ["Cheetah", "Lion", "Horse", "Gazelle"],
        answer: "Cheetah"
    },

    {
        text: "How many continents are there?",
        choices: ["5", "6", "7", "8"],
        answer: "7"
    },

    {
        text: "What is the square root of 81?",
        choices: ["7", "8", "9", "10"],
        answer: "9"
    },

    {
        text: "Which gas do plants absorb from the atmosphere?",
        choices: ["Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"],
        answer: "Carbon Dioxide"
    },

]



function getRandomRewards(effects) {
    const shuffled = effects.slice().sort(() => Math.random() - 0.5);
}

/* random selcetec rewards for the user to choose from */

const effects = [
  { description: "+200% Gold", type: "PERCENTAGE_MULTIPLIER", value: 3.0 },
  { description: "+300% Gold", type: "PERCENTAGE_MULTIPLIER", value: 4.0 },
  { description: "-99% Gold", type: "PERCENTAGE_MULTIPLIER", value: 0.01 },
  { description: "-20% Gold", type: "PERCENTAGE_MULTIPLIER", value: 0.8 },
  { description: "-50% Gold", type: "PERCENTAGE_MULTIPLIER", value: 0.5 },
  { description: "+250 Gold", type: "FLAT_AMOUNT", value: 250.0 },
  { description: "+10 Gold", type: "FLAT_AMOUNT", value: 10.0 },
  { description: "+500 Gold", type: "FLAT_AMOUNT", value: 500.0 },
  { description: "-100 Gold", type: "FLAT_AMOUNT", value: -100.0 },
  { description: "-200 Gold", type: "FLAT_AMOUNT", value: -200.0 },
  { description: "No Change", type: "FLAT_AMOUNT", value: 0.0 }
];

let currentQuestion = 0;

/*function for question and buttons*/

function showQuestion(index) {
    const q = questions[index];
    document.getElementById('question-box').innerHTML = `
        <h2>Question ${index + 1}: ${q.text}</h2>
        <div class="answer-grid">
            <button class="answer-btn btn-a">A. ${q.choices[0]}</button>
            <button class="answer-btn btn-b">B. ${q.choices[1]}</button>
            <button class="answer-btn btn-c">C. ${q.choices[2]}</button>
            <button class="answer-btn btn-d">D. ${q.choices[3]}</button>
        </div>
        <div id = "feedback" style="margin-top:16px; font-weight:bold;"></div>
        <button id="next-question-btn" style="margin-top: 16px;">Next</button>
    `;
}

document.getElementById('next-btn').addEventListener('click', function() {
    document.getElementById('greetings').style.display = 'none';
    document.getElementById('description').style.display = 'none';
    this.style.display = 'none';
    showQuestion(currentQuestion);

});

    document.addEventListener("click", function(e) {
        if (e.target && e.target.id === 'next-question-btn') {
            currentQuestion++;
            if (currentQuestion < questions.length) {
                showQuestion(currentQuestion);
            } else {
                document.getElementById("question-box").innerHTML = "<h2>Quiz Complete!</h2>";
            }
        }
    });

    /* checks users answers and confirms if correct or not */

    document.addEventListener('click', function(e) {
        if (e.target.classList.contains('answer-btn')) {
            const userAnswer = e.target.textContent.slice(3).trim();
            const correctAnswer = questions[currentQuestion].answer;
            const feedback = document.getElementById('feedback');

        if (userAnswer === correctAnswer){
            feedback.textContent = "Correct!";
            feedback.style.color = "green";

        } else {
             feedback.textContent = "Wrong!";
            feedback.style.color = "red";
        }

   
    }
});
