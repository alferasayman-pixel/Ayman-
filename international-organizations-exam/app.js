function cleanAcademicText(value){
  return String(value??'').replace(/الملف/g,'المقرر').replace(/\s{2,}/g,' ').trim();
}

(window.TESTS||[]).forEach(test=>{
  test.title=cleanAcademicText(test.title);
  test.subtitle=cleanAcademicText(test.subtitle);
  (test.questions||[]).forEach(question=>{
    question.statement=cleanAcademicText(question.statement);
    question.explanation=cleanAcademicText(question.explanation);
  });
});

const state={test:null,index:0,answers:{},order:[],student:localStorage.getItem('io_student')||''};
const $=selector=>document.querySelector(selector);
const home=$('#homeView');
const exam=$('#examView');
const result=$('#resultView');

$('#studentName').value=state.student;

function esc(value){
  return String(value).replace(/[&<>'"]/g,char=>({'&':'&amp;','<':'&lt;','>':'&gt;',"'":'&#039;','"':'&quot;'}[char]));
}

function toast(message){
  const element=$('#toast');
  element.textContent=message;
  element.classList.add('show');
  setTimeout(()=>element.classList.remove('show'),1800);
}

$('#saveName').onclick=()=>{
  state.student=$('#studentName').value.trim();
  localStorage.setItem('io_student',state.student);
  toast('تم حفظ الاسم');
};

function storageKey(id){return 'io_exam_'+id;}
function resultKey(id){return 'io_result_'+id;}

function shuffle(items){
  const result=[...items];
  for(let i=result.length-1;i>0;i--){
    const j=Math.floor(Math.random()*(i+1));
    [result[i],result[j]]=[result[j],result[i]];
  }
  return result;
}

function renderCards(){
  const grid=$('#testsGrid');
  grid.innerHTML='';
  (window.TESTS||[]).forEach(test=>{
    const saved=JSON.parse(localStorage.getItem(storageKey(test.id))||'null');
    const done=JSON.parse(localStorage.getItem(resultKey(test.id))||'null');
    const count=saved?Object.keys(saved.answers||{}).length:0;
    const card=document.createElement('article');
    card.className='test-card';
    card.innerHTML=`<div class="test-number">${String(test.id).padStart(2,'0')}</div><div class="test-info"><h4>${esc(test.title)}</h4><p>${esc(test.subtitle)}</p><div class="meta"><span class="tag">50 سؤالاً</span><span class="tag">صح / خطأ</span>${done?`<span class="tag">آخر نتيجة: ${done.score}/50</span>`:count?`<span class="tag">محفوظ: ${count}/50</span>`:''}</div></div><button class="card-action" aria-label="بدء الاختبار">←</button>`;
    card.onclick=()=>startTest(test.id);
    grid.appendChild(card);
  });
}

function startTest(id){
  const test=(window.TESTS||[]).find(item=>item.id===id);
  const saved=JSON.parse(localStorage.getItem(storageKey(id))||'null');
  state.test=test;
  state.answers=saved?.answers||{};
  state.order=saved?.order?.length===50?saved.order:shuffle(test.questions.map((_,index)=>index));
  state.index=saved?.index||0;
  saveProgress();
  home.classList.add('hidden');
  result.classList.add('hidden');
  exam.classList.remove('hidden');
  renderQuestion();
  window.scrollTo({top:0});
}

function saveProgress(){
  if(!state.test)return;
  localStorage.setItem(storageKey(state.test.id),JSON.stringify({answers:state.answers,order:state.order,index:state.index}));
}

function currentQuestion(){
  return state.test.questions[state.order[state.index]];
}

function answerButtonClass(buttonValue,question){
  const userAnswer=state.answers[question.id];
  const answered=userAnswer!==undefined;
  let className='answer-btn';
  if(userAnswer===buttonValue)className+=' selected';
  if(answered){
    className+=' locked';
    if(question.answer===buttonValue)className+=' correct';
    if(userAnswer===buttonValue&&question.answer!==buttonValue)className+=' wrong';
  }
  return className;
}

function feedbackHTML(question){
  const userAnswer=state.answers[question.id];
  if(userAnswer===undefined)return '';
  const isCorrect=userAnswer===question.answer;
  const correctLabel=question.answer?'صح':'خطأ';
  const explanation=esc(cleanAcademicText(question.explanation));
  if(isCorrect){
    return `<div class="instant-feedback ok"><div class="status">✅ إجابة صحيحة</div>${explanation?`<span class="exp">${explanation}</span>`:''}</div>`;
  }
  return `<div class="instant-feedback bad"><div class="status">❌ إجابة خاطئة</div><div class="correction-line">التصحيح: الإجابة الصحيحة هي <span class="correct-answer">${correctLabel}</span></div>${explanation?`<span class="exp">${explanation}</span>`:''}</div>`;
}

function renderQuestion(){
  const answered=Object.keys(state.answers).length;
  const question=currentQuestion();
  const locked=state.answers[question.id]!==undefined;
  exam.innerHTML=`<div class="exam-shell"><aside class="exam-side"><h3>${esc(state.test.title)}</h3><p>${esc(state.test.subtitle)}</p><div class="student">الطالب: <strong>${esc(state.student||'غير مسجل')}</strong></div><div class="progress-wrap"><div class="progress-label"><span>التقدم</span><span>${answered}/50</span></div><div class="progress"><div style="width:${answered*2}%"></div></div></div><div class="q-nav" id="qNav"></div><button class="danger-link" id="exitBtn">حفظ وخروج</button></aside><article class="question-card"><div class="q-kicker">السؤال ${state.index+1} من 50</div><h2>${esc(cleanAcademicText(question.statement))}</h2><div class="answer-grid"><button class="${answerButtonClass(true,question)}" data-v="true" ${locked?'disabled':''}><span class="answer-icon">✓</span>صح</button><button class="${answerButtonClass(false,question)}" data-v="false" ${locked?'disabled':''}><span class="answer-icon">✕</span>خطأ</button></div>${feedbackHTML(question)}<div class="question-footer"><button class="navbtn prev" id="prevBtn" ${state.index===0?'disabled':''}>السابق</button><button class="navbtn next" id="nextBtn">${state.index===49?'مراجعة الإنهاء':'التالي'}</button></div>${answered===50?'<div class="submit-row"><button class="submit" id="submitBtn">إنهاء الاختبار وإظهار النتيجة</button></div>':''}</article></div>`;

  const nav=$('#qNav');
  state.order.forEach((questionIndex,index)=>{
    const item=state.test.questions[questionIndex];
    const userAnswer=state.answers[item.id];
    const correct=userAnswer!==undefined&&userAnswer===item.answer;
    const wrong=userAnswer!==undefined&&userAnswer!==item.answer;
    const button=document.createElement('button');
    button.className='q-dot'+(userAnswer!==undefined?' answered':'')+(index===state.index?' current':'')+(correct?' correct':'')+(wrong?' wrong':'');
    button.textContent=index+1;
    button.onclick=()=>{
      state.index=index;
      saveProgress();
      renderQuestion();
    };
    nav.appendChild(button);
  });

  document.querySelectorAll('.answer-btn').forEach(button=>{
    button.onclick=()=>{
      if(state.answers[question.id]!==undefined)return;
      state.answers[question.id]=button.dataset.v==='true';
      saveProgress();
      renderQuestion();
    };
  });

  $('#prevBtn').onclick=()=>{
    if(state.index>0){
      state.index--;
      saveProgress();
      renderQuestion();
    }
  };

  $('#nextBtn').onclick=()=>{
    if(state.index<49){
      state.index++;
      saveProgress();
      renderQuestion();
    }else if(Object.keys(state.answers).length<50){
      const firstUnanswered=state.order.findIndex(questionIndex=>state.answers[state.test.questions[questionIndex].id]===undefined);
      if(firstUnanswered>=0){
        state.index=firstUnanswered;
        saveProgress();
        renderQuestion();
        toast('أكمل الأسئلة غير المجابة');
      }
    }
  };

  $('#exitBtn').onclick=goHome;
  const submitButton=$('#submitBtn');
  if(submitButton)submitButton.onclick=submitTest;
}

function submitTest(){
  if(Object.keys(state.answers).length<50){
    toast('يرجى الإجابة عن جميع الأسئلة');
    return;
  }
  let score=0;
  state.test.questions.forEach(question=>{
    if(state.answers[question.id]===question.answer)score++;
  });
  localStorage.setItem(resultKey(state.test.id),JSON.stringify({score,date:new Date().toISOString()}));
  localStorage.removeItem(storageKey(state.test.id));
  renderResult(score);
}

function renderResult(score){
  exam.classList.add('hidden');
  result.classList.remove('hidden');
  const percentage=Math.round(score*2);
  const level=percentage>=90?'ممتاز':percentage>=80?'جيد جداً':percentage>=70?'جيد':percentage>=60?'مقبول':'يحتاج إلى مراجعة';
  const ordered=state.order.map(index=>state.test.questions[index]);
  result.innerHTML=`<article class="result-card"><div class="score-circle" style="--pct:${percentage}%"><div class="score-inner"><b>${score}/50</b><span>${percentage}%</span></div></div><h2>${level}</h2><p class="result-summary">${esc(state.student||'الطالب')}، أنهيت ${esc(state.test.title)}. راجع البنود أدناه لتثبيت المعلومات.</p><div class="result-actions"><button class="primary" id="homeBtn">العودة للاختبارات</button><button class="secondary" id="retryBtn">إعادة الاختبار</button><button class="secondary" onclick="window.print()">طباعة النتيجة</button></div><div class="review"><h3>المراجعة التفصيلية</h3>${ordered.map((question,index)=>{const correct=state.answers[question.id]===question.answer;return `<div class="review-item ${correct?'correct':'wrong'}"><h4>${index+1}. ${esc(cleanAcademicText(question.statement))}</h4><div class="review-meta">إجابتك: <b>${state.answers[question.id]?'صح':'خطأ'}</b> | الإجابة الصحيحة: <b>${question.answer?'صح':'خطأ'}</b>${question.explanation?`<br>${esc(cleanAcademicText(question.explanation))}`:''}</div></div>`;}).join('')}</div></article>`;

  $('#homeBtn').onclick=goHome;
  $('#retryBtn').onclick=()=>{
    state.answers={};
    state.order=shuffle(state.test.questions.map((_,index)=>index));
    state.index=0;
    localStorage.removeItem(storageKey(state.test.id));
    exam.classList.remove('hidden');
    result.classList.add('hidden');
    saveProgress();
    renderQuestion();
    window.scrollTo({top:0});
  };
  window.scrollTo({top:0});
}

function goHome(){
  exam.classList.add('hidden');
  result.classList.add('hidden');
  home.classList.remove('hidden');
  renderCards();
  window.scrollTo({top:0});
}

renderCards();
