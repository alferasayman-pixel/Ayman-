package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

// --- DESIGN SYSTEM THEME COLORS ---
val ColorNaturalBg = Color(0xFFF5F5F0)
val ColorNaturalInk = Color(0xFF3A3A2F)
val ColorNaturalOlive = Color(0xFF5A5A40)
val ColorNaturalAccent = Color(0xFFC2705F)
val ColorNaturalSidebar = Color(0xFFE9E4D9)
val ColorNaturalBorder = Color(0xFFD9D1C2)
val ColorNaturalMuted = Color(0xFF8B8B7A)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = ColorNaturalBg
                ) {
                    val gameViewModel: GameViewModel = viewModel()
                    GameApp(viewModel = gameViewModel)
                }
            }
        }
    }
}

enum class GameState {
    MENU, PLAYING, RESULTS
}

class GameViewModel : ViewModel() {
    var gameState by mutableStateOf(GameState.MENU)
        private set

    var currentMode by mutableStateOf<QuestionType?>(null)
        private set

    var filteredQuestions by mutableStateOf<List<Question>>(emptyList())
        private set

    var currentIndex by mutableStateOf(0)
        private set

    var score by mutableStateOf(0)
        private set

    var showFeedback by mutableStateOf(false)
        private set

    var selectedAnswer by mutableStateOf<String?>(null)
        private set

    var isCorrect by mutableStateOf<Boolean?>(null)
        private set

    fun startNewGame(mode: QuestionType?) {
        currentMode = mode
        val pool = if (mode == null) {
            QuestionsData.questions
        } else {
            QuestionsData.questions.filter { it.type == mode }
        }
        filteredQuestions = pool.shuffled()
        currentIndex = 0
        score = 0
        showFeedback = false
        selectedAnswer = null
        isCorrect = null
        gameState = GameState.PLAYING
    }

    fun submitAnswer(answer: String) {
        if (showFeedback) return
        selectedAnswer = answer
        val currentQuestion = filteredQuestions[currentIndex]
        val correct = answer.trim().lowercase() == currentQuestion.correctAnswer.trim().lowercase()
        isCorrect = correct
        if (correct) {
            score++
        }
        showFeedback = true
    }

    fun nextQuestion() {
        if (currentIndex < filteredQuestions.size - 1) {
            currentIndex++
            showFeedback = false
            selectedAnswer = null
            isCorrect = null
        } else {
            gameState = GameState.RESULTS
        }
    }

    fun resetGame() {
        gameState = GameState.MENU
        currentMode = null
        filteredQuestions = emptyList()
        currentIndex = 0
        score = 0
        showFeedback = false
        selectedAnswer = null
        isCorrect = null
    }
}

@Composable
fun GameApp(viewModel: GameViewModel) {
    when (viewModel.gameState) {
        GameState.MENU -> MenuScreen(onStart = { mode -> viewModel.startNewGame(mode) })
        GameState.PLAYING -> PlayingScreen(viewModel = viewModel)
        GameState.RESULTS -> ResultsScreen(
            score = viewModel.score,
            total = viewModel.filteredQuestions.size,
            onReset = { viewModel.resetGame() }
        )
    }
}

@Composable
fun MenuScreen(onStart: (QuestionType?) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Hero Header Card
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = ColorNaturalOlive),
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(ColorNaturalSidebar, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Medical Icon",
                        tint = ColorNaturalOlive,
                        modifier = Modifier.size(36.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Pediatric Rash Detective",
                    fontFamily = FontFamily.Serif,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 36.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Educational Simulation: Fever & Exanthem Challenge",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic,
                    color = ColorNaturalSidebar.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Intake Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .background(Color.White, RoundedCornerShape(24.dp))
                .border(1.dp, ColorNaturalBorder, RoundedCornerShape(24.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Clinical Intake",
                fontFamily = FontFamily.Serif,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = ColorNaturalInk
            )
            Text(
                text = "Welcome, medical investigator. Test your clinical knowledge on pediatric differential diagnosis, focusing on hallmark symptoms, complications, and management of major exanthematous illnesses.",
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp,
                color = ColorNaturalMuted,
                lineHeight = 22.sp
            )

            HorizontalDivider(color = ColorNaturalBorder, thickness = 1.dp)

            Text(
                text = "Select Simulation Type",
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                fontWeight = FontWeight.Black,
                color = ColorNaturalOlive,
                letterSpacing = 1.5.sp
            )

            MenuCardButton(
                onClick = { onStart(null) },
                icon = Icons.Default.Star,
                title = "Full Challenge",
                description = "Comprehensive 50-question clinical board prep",
                isAccent = true,
                testTag = "mode_full_challenge"
            )

            MenuCardButton(
                onClick = { onStart(QuestionType.mcq) },
                icon = Icons.Default.Search,
                title = "Board Review",
                description = "30 MCQ Practice Cases",
                isAccent = false,
                testTag = "mode_mcq"
            )

            MenuCardButton(
                onClick = { onStart(QuestionType.case) },
                icon = Icons.Default.Info,
                title = "Clinical Rounds",
                description = "10 Interactive Case Simulations",
                isAccent = false,
                testTag = "mode_case"
            )

            MenuCardButton(
                onClick = { onStart(QuestionType.tf) },
                icon = Icons.Default.CheckCircle,
                title = "Rapid Fire Blitz",
                description = "10 Intensive True/False cases",
                isAccent = false,
                testTag = "mode_tf"
            )
        }

        // Footer Information
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info Icon",
                    tint = ColorNaturalMuted,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Simulation Only",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorNaturalMuted
                )
            }
            Text(
                text = "v.2024 PROTOCOL",
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = ColorNaturalMuted,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun MenuCardButton(
    onClick: () -> Unit,
    icon: ImageVector,
    title: String,
    description: String,
    isAccent: Boolean,
    testTag: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = if (isAccent) null else borderStroke(),
        colors = CardDefaults.cardColors(
            containerColor = if (isAccent) ColorNaturalOlive else Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        if (isAccent) ColorNaturalSidebar else ColorNaturalSidebar.copy(alpha = 0.5f),
                        RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = ColorNaturalOlive,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        fontFamily = FontFamily.Serif,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isAccent) Color.white() else ColorNaturalInk
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Next",
                        tint = if (isAccent) Color.white() else ColorNaturalOlive,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isAccent) ColorNaturalSidebar.copy(alpha = 0.9f) else ColorNaturalMuted,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlayingScreen(viewModel: GameViewModel) {
    val currentQuestion = viewModel.filteredQuestions[viewModel.currentIndex]
    val focusManager = LocalFocusManager.current
    var textAnswer by remember(currentQuestion.id) { mutableStateOf("") }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorNaturalBg)
                .padding(innerPadding)
        ) {
            // Adaptive Two-pane structure for larger screens, standard scrollable for portrait.
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header Details
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 650.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = currentQuestion.type.label(),
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = ColorNaturalOlive,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Case ${viewModel.currentIndex + 1} of ${viewModel.filteredQuestions.size}",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = ColorNaturalMuted
                        )
                    }

                    Card(
                        shape = CircleShape,
                        border = borderStroke(),
                        colors = CardDefaults.cardColors(
                            containerColor = when (currentQuestion.difficulty) {
                                Difficulty.Easy -> Color(0xFFE8F5E9)
                                Difficulty.Moderate -> Color(0xFFFFF3E0)
                                Difficulty.Advanced -> Color(0xFFFFEBEE)
                            }
                        )
                    ) {
                        Text(
                            text = currentQuestion.difficulty.name,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            color = when (currentQuestion.difficulty) {
                                Difficulty.Easy -> Color(0xFF2E7D32)
                                Difficulty.Moderate -> Color(0xFFEF6C00)
                                Difficulty.Advanced -> Color(0xFFC62828)
                            },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                // Smooth Progress Indicator
                val progress = (viewModel.currentIndex + 1).toFloat() / viewModel.filteredQuestions.size
                LinearProgressIndicator(
                    progress = { progress },
                    color = ColorNaturalOlive,
                    trackColor = ColorNaturalBorder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 650.dp)
                        .height(6.dp)
                        .clip(CircleShape)
                )

                // Visual Matrix Dots - beautifully fitting on screen
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 650.dp)
                        .background(ColorNaturalSidebar.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    maxItemsInEachRow = 15
                ) {
                    viewModel.filteredQuestions.forEachIndexed { index, _ ->
                        val dotColor = when {
                            index < viewModel.currentIndex -> ColorNaturalOlive
                            index == viewModel.currentIndex -> ColorNaturalAccent
                            else -> ColorNaturalBorder
                        }
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 2.dp)
                                .size(width = 12.dp, height = 4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(dotColor)
                        )
                    }
                }

                // Question Prompt
                Text(
                    text = currentQuestion.question,
                    fontFamily = FontFamily.Serif,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ColorNaturalInk,
                    textAlign = TextAlign.Start,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 650.dp)
                        .padding(vertical = 8.dp)
                )

                // Answer Area Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 650.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = borderStroke()
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        // Diagnostic Inputs
                        if (currentQuestion.type == QuestionType.case) {
                            Text(
                                text = "Formulate your diagnosis:",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = ColorNaturalInk,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            OutlinedTextField(
                                value = textAnswer,
                                onValueChange = { textAnswer = it },
                                placeholder = {
                                    Text(
                                        "Formulate your diagnostic conclusion here...",
                                        fontSize = 14.sp,
                                        color = ColorNaturalMuted.copy(alpha = 0.6f)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .testTag("case_answer_input"),
                                enabled = !viewModel.showFeedback,
                                shape = RoundedCornerShape(16.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = ColorNaturalInk,
                                    unfocusedTextColor = ColorNaturalInk,
                                    focusedContainerColor = ColorNaturalBg.copy(alpha = 0.2f),
                                    unfocusedContainerColor = Color.White,
                                    focusedIndicatorColor = ColorNaturalOlive,
                                    unfocusedIndicatorColor = ColorNaturalBorder,
                                    cursorColor = ColorNaturalOlive
                                ),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(onDone = {
                                    focusManager.clearFocus()
                                    viewModel.submitAnswer(textAnswer)
                                })
                            )

                            if (!viewModel.showFeedback) {
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {
                                        focusManager.clearFocus()
                                        viewModel.submitAnswer(textAnswer)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                        .testTag("submit_diagnosis_button"),
                                    shape = RoundedCornerShape(28.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = ColorNaturalOlive)
                                ) {
                                    Text(
                                        text = "SUBMIT DIAGNOSIS",
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.5.sp
                                    )
                                }
                            }
                        } else {
                            // Multiple Choice Options or True / False Buttons
                            val options = currentQuestion.options ?: listOf("True", "False")
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                options.forEachIndexed { index, option ->
                                    val isSelected = viewModel.selectedAnswer == option
                                    val isCorrectOption = option == currentQuestion.correctAnswer
                                    val optionLabel = if (currentQuestion.type == QuestionType.tf) {
                                        if (index == 0) "T" else "F"
                                    } else {
                                        ('A' + index).toString()
                                    }

                                    AnswerOptionRow(
                                        optionText = option,
                                        label = optionLabel,
                                        isSelected = isSelected,
                                        showFeedback = viewModel.showFeedback,
                                        isCorrect = isCorrectOption,
                                        onClick = { viewModel.submitAnswer(option) },
                                        testTag = "option_${optionLabel.lowercase()}"
                                    )
                                }
                            }
                        }

                        // Feedback panel
                        AnimatedVisibility(
                            visible = viewModel.showFeedback,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Column(modifier = Modifier.padding(top = 24.dp)) {
                                val isCorrect = viewModel.isCorrect == true
                                Card(
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isCorrect) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .background(
                                                    if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                                                    CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Warning,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column {
                                            Text(
                                                text = if (isCorrect) "Diagnostic Concordance" else "Diagnostic Divergence",
                                                fontFamily = FontFamily.Serif,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isCorrect) Color(0xFF1B5E20) else Color(0xFFB71C1C)
                                            )
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = "Pathognomonic Match: ${currentQuestion.correctAnswer}",
                                                fontFamily = FontFamily.SansSerif,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isCorrect) Color(0xFF1B5E20) else Color(0xFFB71C1C)
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Explanatory clinical block
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(ColorNaturalSidebar.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                                        .border(1.dp, ColorNaturalBorder, RoundedCornerShape(20.dp))
                                        .padding(20.dp)
                                ) {
                                    Column {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Info,
                                                contentDescription = null,
                                                tint = ColorNaturalOlive,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                text = "Clinical Contextualization",
                                                fontFamily = FontFamily.SansSerif,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Black,
                                                color = ColorNaturalOlive,
                                                letterSpacing = 1.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = currentQuestion.explanation,
                                            fontFamily = FontFamily.SansSerif,
                                            fontSize = 14.sp,
                                            color = ColorNaturalInk,
                                            lineHeight = 22.sp
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(24.dp))

                                Button(
                                    onClick = { viewModel.nextQuestion() },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                        .testTag("next_case_button"),
                                    shape = RoundedCornerShape(28.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = ColorNaturalOlive)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Text(
                                            text = "NEXT CASE STUDY",
                                            fontFamily = FontFamily.SansSerif,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 1.5.sp
                                        )
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Bottom Game Stats
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 650.dp)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "← Exit Rounds",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorNaturalMuted,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { viewModel.resetGame() }
                    )
                    Text(
                        text = "${viewModel.score} Correct  •  ${viewModel.filteredQuestions.size - viewModel.currentIndex - 1} Remaining",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorNaturalMuted
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerOptionRow(
    optionText: String,
    label: String,
    isSelected: Boolean,
    showFeedback: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit,
    testTag: String
) {
    val borderColor = when {
        showFeedback && isCorrect -> Color(0xFF2E7D32)
        showFeedback && isSelected && !isCorrect -> ColorNaturalAccent
        isSelected -> ColorNaturalOlive
        else -> ColorNaturalBorder
    }

    val containerColor = when {
        showFeedback && isCorrect -> Color(0xFFE8F5E9)
        showFeedback && isSelected && !isCorrect -> Color(0xFFFFEBEE)
        isSelected -> ColorNaturalSidebar
        else -> Color.White
    }

    val labelColor = when {
        showFeedback && isCorrect -> Color.White
        isSelected -> Color.White
        else -> ColorNaturalOlive
    }

    val labelBg = when {
        showFeedback && isCorrect -> Color(0xFF2E7D32)
        isSelected -> ColorNaturalOlive
        else -> ColorNaturalSidebar
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        border = borderStroke(borderColor = borderColor),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag)
            .clickable(enabled = !showFeedback, onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(labelBg, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = labelColor
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = optionText,
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = if (showFeedback && !isCorrect && !isSelected) ColorNaturalMuted.copy(alpha = 0.5f) else ColorNaturalInk,
                modifier = Modifier.weight(1f)
            )
            if (showFeedback) {
                if (isCorrect) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Correct",
                        tint = Color(0xFF2E7D32),
                        modifier = Modifier.size(24.dp)
                    )
                } else if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Incorrect",
                        tint = ColorNaturalAccent,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ResultsScreen(score: Int, total: Int, onReset: () -> Unit) {
    val percentage = if (total > 0) Math.round((score.toFloat() / total) * 100) else 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 500.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = borderStroke()
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(ColorNaturalSidebar, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Trophy",
                        tint = ColorNaturalOlive,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Text(
                    text = "Diagnostic Summary",
                    fontFamily = FontFamily.Serif,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorNaturalInk
                )

                Text(
                    text = "Case Review Complete: Correctly analyzed $score of $total pathologies.",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    color = ColorNaturalMuted,
                    textAlign = TextAlign.Center
                )

                // Custom Circular Score Ring
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .border(10.dp, ColorNaturalSidebar, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$percentage%",
                            fontFamily = FontFamily.Serif,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Black,
                            color = ColorNaturalOlive
                        )
                    }
                }

                Button(
                    onClick = onReset,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("reset_rounds_button"),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ColorNaturalOlive)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "RESET ROUNDS",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )
                    }
                }

                Text(
                    text = "TARGET COMPETENCY: 80%",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = ColorNaturalMuted,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

// --- HELPERS ---
fun borderStroke(borderColor: Color = ColorNaturalBorder) = androidx.compose.foundation.BorderStroke(1.dp, borderColor)
fun Color.Companion.white() = Color.White
