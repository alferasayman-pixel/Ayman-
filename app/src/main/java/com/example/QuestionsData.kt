package com.example

object QuestionsData {
    val questions = listOf(
        // --- MULTIPLE CHOICE QUESTIONS (30) ---
        Question(
            id = "mcq-1",
            type = QuestionType.mcq,
            topic = "Kawasaki Disease",
            difficulty = Difficulty.Easy,
            question = "What is the minimum duration of fever required for a classic diagnosis of Kawasaki Disease?",
            options = listOf("3 days", "5 days", "7 days", "10 days"),
            correctAnswer = "5 days",
            explanation = "According to diagnostic criteria, a high persistent fever (≥39–40 °C) for ≥5 days is a primary requirement for Kawasaki Disease."
        ),
        Question(
            id = "mcq-2",
            type = QuestionType.mcq,
            topic = "Measles",
            difficulty = Difficulty.Moderate,
            question = "Which sign is pathognomonic for Measles and appears on the buccal mucosa ~2 days before the rash?",
            options = listOf("Forchheimer spots", "Nagayama spots", "Koplik's spots", "Pastia's lines"),
            correctAnswer = "Koplik's spots",
            explanation = "Koplik's spots are tiny blue-white papules with a red halo inside the cheeks, pathognomonic for Measles."
        ),
        Question(
            id = "mcq-3",
            type = QuestionType.mcq,
            topic = "Varicella",
            difficulty = Difficulty.Easy,
            question = "What is the classic description of a Varicella (Chickenpox) lesion?",
            options = listOf("Sandpaper-like texture", "Slapped-cheek appearance", "Dew drop on a rose petal", "Lacy reticulated pattern"),
            correctAnswer = "Dew drop on a rose petal",
            explanation = "Varicella lesions are described as oval teardrop vesicles on a red base, often called \"dew drops on a rose petal.\""
        ),
        Question(
            id = "mcq-4",
            type = QuestionType.mcq,
            topic = "Fifth Disease",
            difficulty = Difficulty.Moderate,
            question = "Which complication of Fifth Disease is particularly dangerous for patients with sickle cell disease?",
            options = listOf("Coronary aneurysms", "Aplastic crisis", "Reye syndrome", "SSPE"),
            correctAnswer = "Aplastic crisis",
            explanation = "Parvovirus B19 (Fifth Disease) can shut down RBC production for 7–10 days, leading to a life-threatening aplastic crisis in patients with hemolytic anemias like sickle cell."
        ),
        Question(
            id = "mcq-5",
            type = QuestionType.mcq,
            topic = "Roseola",
            difficulty = Difficulty.Easy,
            question = "In Roseola Infantum, when does the rash typically appear in relation to the fever?",
            options = listOf("Simultaneous with fever onset", "Before the fever starts", "After the fever abates", "During the 3rd day of fever"),
            correctAnswer = "After the fever abates",
            explanation = "In Roseola, the rose-pink blanching rash uniquely appears after the abrupt high fever suddenly resolves (defervescence)."
        ),
        Question(
            id = "mcq-6",
            type = QuestionType.mcq,
            topic = "Scarlet Fever",
            difficulty = Difficulty.Moderate,
            question = "What is the first-line antibiotic treatment for Scarlet Fever to prevent Rheumatic Fever?",
            options = listOf("Doxycycline", "Acyclovir", "Penicillin or Amoxicillin", "IVIG"),
            correctAnswer = "Penicillin or Amoxicillin",
            explanation = "Penicillin or Amoxicillin for 10 days is the first-line treatment to eradicate Group A Strep (GAS) and prevent Rheumatic Fever."
        ),
        Question(
            id = "mcq-7",
            type = QuestionType.mcq,
            topic = "Meningococcal Disease",
            difficulty = Difficulty.Advanced,
            question = "What is the specific adrenal complication associated with fulminant meningococcemia?",
            options = listOf("Addisonians crisis", "Waterhouse-Friderichsen syndrome", "Cushing syndrome", "Conn syndrome"),
            correctAnswer = "Waterhouse-Friderichsen syndrome",
            explanation = "Waterhouse-Friderichsen syndrome involves adrenal hemorrhage leading to acute adrenal insufficiency and cardiovascular collapse in meningococcemia."
        ),
        Question(
            id = "mcq-8",
            type = QuestionType.mcq,
            topic = "RMSF",
            difficulty = Difficulty.Advanced,
            question = "Rocky Mountain Spotted Fever rash typically begins where?",
            options = listOf("Face and hairlines", "Trunk and spreads outwards", "Wrists, ankles, and forearms", "Palms and soles only"),
            correctAnswer = "Wrists, ankles, and forearms",
            explanation = "RMSF rash starts on the wrists, ankles, and forearms (centripetal distribution) and spreads to the trunk."
        ),
        Question(
            id = "mcq-9",
            type = QuestionType.mcq,
            topic = "Kawasaki Disease",
            difficulty = Difficulty.Moderate,
            question = "The cervical lymphadenopathy in Kawasaki Disease is characteristically:",
            options = listOf("Bilateral and <1 cm", "Unilateral and ≥1.5 cm", "Generalized and tender", "Supraclavicular"),
            correctAnswer = "Unilateral and ≥1.5 cm",
            explanation = "Kawasaki diagnostic criteria include cervical lymphadenopathy that is typically unilateral and ≥1.5 cm."
        ),
        Question(
            id = "mcq-10",
            type = QuestionType.mcq,
            topic = "Measles",
            difficulty = Difficulty.Moderate,
            question = "What dietary supplement is recommended for children hospitalized with Measles to reduce morbidity?",
            options = listOf("Vitamin C", "Vitamin D", "Vitamin A", "Iron"),
            correctAnswer = "Vitamin A",
            explanation = "Vitamin A supplementation (two doses 24 hours apart) is recommended for hospitalized or malnourished children with Measles to reduce complications."
        ),
        Question(
            id = "mcq-11",
            type = QuestionType.mcq,
            topic = "Rubella",
            difficulty = Difficulty.Easy,
            question = "Rubella is often referred to as \"___-day measles\" due to the typical duration of its rash.",
            options = listOf("1", "3", "5", "7"),
            correctAnswer = "3",
            explanation = "Rubella is commonly called \"3-day measles\" because the rash typically resolves by the 3rd day."
        ),
        Question(
            id = "mcq-12",
            type = QuestionType.mcq,
            topic = "Hand-Foot-Mouth Disease",
            difficulty = Difficulty.Easy,
            question = "Which virus is a common cause of Hand-Foot-Mouth Disease outbreaks?",
            options = listOf("HHV-6", "Parvovirus B19", "Coxsackievirus A16", "Rickettsia rickettsii"),
            correctAnswer = "Coxsackievirus A16",
            explanation = "Coxsackievirus A16 and Enterovirus 71 are the most common pathogens causing Hand-Foot-Mouth Disease."
        ),
        Question(
            id = "mcq-13",
            type = QuestionType.mcq,
            topic = "Scarlet Fever",
            difficulty = Difficulty.Moderate,
            question = "Accentuation of the rash in skin creases, such as the axillae or antecubital fossa, is known as:",
            options = listOf("Koplik spots", "Pastia's lines", "Nagayama spots", "Target lesions"),
            correctAnswer = "Pastia's lines",
            explanation = "Pastia's lines are linear red streaks in skin creases (axillae, groin, elbow folds) characteristic of Scarlet Fever."
        ),
        Question(
            id = "mcq-14",
            type = QuestionType.mcq,
            topic = "Varicella",
            difficulty = Difficulty.Advanced,
            question = "Acyclovir is recommended for Varicella if the patient is:",
            options = listOf("Under 2 years old", "A healthy child under 12", "A teen ≥13 or adult", "Already crusted over"),
            correctAnswer = "A teen ≥13 or adult",
            explanation = "Acyclovir is recommended to shorten the illness in teens ≥13, adults, and those with chronic skin/lung disease."
        ),
        Question(
            id = "mcq-15",
            type = QuestionType.mcq,
            topic = "Kawasaki Disease",
            difficulty = Difficulty.Moderate,
            question = "Which imaging study is critical at diagnosis of Kawasaki Disease to screen for complications?",
            options = listOf("Chest X-ray", "CT Head", "Echocardiography", "Abdominal Ultrasound"),
            correctAnswer = "Echocardiography",
            explanation = "Echocardiography is performed at diagnosis and repeat intervals to monitor for coronary artery aneurysms."
        ),
        Question(
            id = "mcq-16",
            type = QuestionType.mcq,
            topic = "Fifth Disease",
            difficulty = Difficulty.Moderate,
            question = "A lace-like, reticulated rash on the trunk and extensor surfaces is characteristic of:",
            options = listOf("Scarlet Fever", "Fifth Disease", "Roseola", "Measles"),
            correctAnswer = "Fifth Disease",
            explanation = "The second stage of Fifth Disease involves a reticulated, lacy rash on the body after the initial \"slapped-cheek\" appearance."
        ),
        Question(
            id = "mcq-17",
            type = QuestionType.mcq,
            topic = "Meningococcal Disease",
            difficulty = Difficulty.Advanced,
            question = "When should antibiotics be started if Meningococcal disease is suspected?",
            options = listOf("After blood culture results return", "After Lumbar Puncture is completed", "Immediately, do not wait for confirmation", "Only if the petechial rash appears"),
            correctAnswer = "Immediately, do not wait for confirmation",
            explanation = "Immediate IV antibiotics are critical; treatment should NOT wait for results or procedures if the disease is suspected."
        ),
        Question(
            id = "mcq-18",
            type = QuestionType.mcq,
            topic = "RMSF",
            difficulty = Difficulty.Moderate,
            question = "What is the treatment of choice for RMSF for all ages?",
            options = listOf("Amoxicillin", "Doxycycline", "Acyclovir", "Ceftriaxone"),
            correctAnswer = "Doxycycline",
            explanation = "Doxycycline is the treatment of choice for Rocky Mountain Spotted Fever for ALL ages, as the benefits outweigh potential risks."
        ),
        Question(
            id = "mcq-19",
            type = QuestionType.mcq,
            topic = "Measles",
            difficulty = Difficulty.Easy,
            question = "The \"3 Cs\" of the Measles prodrome include Cough, Conjunctivitis, and:",
            options = listOf("Croup", "Cold hands", "Coryza", "Cracked lips"),
            correctAnswer = "Coryza",
            explanation = "The classic Measles prodromal 3 Cs are Cough, Coryza (runny nose), and Conjunctivitis."
        ),
        Question(
            id = "mcq-20",
            type = QuestionType.mcq,
            topic = "Roseola",
            difficulty = Difficulty.Moderate,
            question = "What percentage of children with Roseola may experience a febrile seizure during the high fever phase?",
            options = listOf("1–2%", "5–10%", "10–15%", "25–30%"),
            correctAnswer = "10–15%",
            explanation = "The study notes that 10–15% of Roseola cases may involve a febrile seizure during the abrupt high fever phase."
        ),
        Question(
            id = "mcq-21",
            type = QuestionType.mcq,
            topic = "Rubella",
            difficulty = Difficulty.Advanced,
            question = "Congenital Rubella Syndrome (CRS) is classically characterized by a triad of deafness, ocular defects, and:",
            options = listOf("Arthritis", "Cardiac defects (e.g., PDA)", "Strawberry tongue", "Pastia's lines"),
            correctAnswer = "Cardiac defects (e.g., PDA)",
            explanation = "The CRS triad includes sensorineural deafness, cardiac defects (PDA, pulmonary artery stenosis), and ocular defects (cataracts)."
        ),
        Question(
            id = "mcq-22",
            type = QuestionType.mcq,
            topic = "Scarlet Fever",
            difficulty = Difficulty.Moderate,
            question = "The \"Strawberry Tongue\" in Scarlet Fever changes from white to red on which day typically?",
            options = listOf("Day 1", "Day 2", "Day 4", "Day 7"),
            correctAnswer = "Day 4",
            explanation = "In Scarlet Fever, the white coating sloughs off by day 4, leaving a glistening \"red strawberry tongue.\""
        ),
        Question(
            id = "mcq-23",
            type = QuestionType.mcq,
            topic = "Varicella",
            difficulty = Difficulty.Moderate,
            question = "Varicella patients are typically considered contagious until:",
            options = listOf("The fever subsides", "The rash begins", "All lesions have crusted over", "Antibiotics are started"),
            correctAnswer = "All lesions have crusted over",
            explanation = "Varicella is contagious from 1–2 days before the rash until every vesicle has crusted over (usually ~1 week)."
        ),
        Question(
            id = "mcq-24",
            type = QuestionType.mcq,
            topic = "Kawasaki Disease",
            difficulty = Difficulty.Advanced,
            question = "What medication should be avoided in Kawasaki disease due to its potential to antagonize aspirin's anti-platelet effect?",
            options = listOf("Acetaminophen", "Ibuprofen", "Diphenhydramine", "Calamine"),
            correctAnswer = "Ibuprofen",
            explanation = "Ibuprofen should be avoided as it may interfere with the anti-platelet effect of low-dose aspirin used in follow-up."
        ),
        Question(
            id = "mcq-25",
            type = QuestionType.mcq,
            topic = "Meningococcal Disease",
            difficulty = Difficulty.Advanced,
            question = "For a child with suspected meningococcal meningitis, which adjunctive therapy may reduce neurologic sequelae?",
            options = listOf("Vitamin A", "High-dose Aspirin", "Dexamethasone", "IVIG"),
            correctAnswer = "Dexamethasone",
            explanation = "Dexamethasone is often given before/with the first antibiotic dose to reduce neurologic risk, particularly in bacterial meningitis."
        ),
        Question(
            id = "mcq-26",
            type = QuestionType.mcq,
            topic = "Hand-Foot-Mouth Disease",
            difficulty = Difficulty.Easy,
            question = "The oral lesions in HFMD typically appear as:",
            options = listOf("Large bloody crusts", "Small painful ulcers on tongue and buccal mucosa", "White plaques that bleed when scraped", "Blue-white papules"),
            correctAnswer = "Small painful ulcers on tongue and buccal mucosa",
            explanation = "HFMD oral lesions start as red spots that evolve into tiny painful ulcers (2–3 mm) on the tongue and oral mucosa."
        ),
        Question(
            id = "mcq-27",
            type = QuestionType.mcq,
            topic = "Measles",
            difficulty = Difficulty.Advanced,
            question = "Which rare but fatal degenerative CNS disease can occur 7–10 years after a Measles infection?",
            options = listOf("Encephalitis", "SSPE", "Reye Syndrome", "Meningitis"),
            correctAnswer = "SSPE",
            explanation = "Subacute sclerosing panencephalitis (SSPE) is a very rare late neurologic complication of Measles."
        ),
        Question(
            id = "mcq-28",
            type = QuestionType.mcq,
            topic = "Fifth Disease",
            difficulty = Difficulty.Easy,
            question = "What is the \"Stage 1\" facial characteristic of Fifth Disease?",
            options = listOf("Circumoral pallor and flushed cheeks", "Koplik spots", "Blue-white papules", "Unilateral lymphadenopathy"),
            correctAnswer = "Circumoral pallor and flushed cheeks",
            explanation = "Fifth Disease \"Stage 1\" is characterized by bright red cheeks (\"slapped-cheek\") with circumoral pallor."
        ),
        Question(
            id = "mcq-29",
            type = QuestionType.mcq,
            topic = "RMSF",
            difficulty = Difficulty.Advanced,
            question = "In RMSF, if untreated, when does the rash typically become petechial?",
            options = listOf("By day 2", "By day 3", "By day 5–6", "After the fever resolves"),
            correctAnswer = "By day 5–6",
            explanation = "The initially blanching macules of RMSF typically become petechial and non-blanching by day 5–6 of illness."
        ),
        Question(
            id = "mcq-30",
            type = QuestionType.mcq,
            topic = "Roseola",
            difficulty = Difficulty.Advanced,
            question = "Nagayama spots, associated with Roseola, are found where?",
            options = listOf("Buccal mucosa", "Soft palate/uvula", "Axilla", "Extensor surfaces"),
            correctAnswer = "Soft palate/uvula",
            explanation = "Nagayama spots are erythematous papules on the soft palate/uvula found in about 2/3 of Roseola cases."
        ),

        // --- CLINICAL CASE QUESTIONS (10) ---
        Question(
            id = "case-1",
            type = QuestionType.case,
            topic = "Kawasaki Disease",
            difficulty = Difficulty.Moderate,
            question = "A 2-year-old child presents with 6 days of high fever (40°C) unresponsive to antipyretics. Findings include bilateral non-exudative conjunctivitis, a strawberry tongue, and unilateral cervical lymphadenopathy. What is the most likely diagnosis?",
            correctAnswer = "Kawasaki Disease",
            explanation = "High fever for ≥5 days plus 4 of 5 \"CRASH\" criteria (Conjunctivitis, Rash, Adenopathy, Strawberry tongue, Hand/foot changes) diagnoses Kawasaki Disease."
        ),
        Question(
            id = "case-2",
            type = QuestionType.case,
            topic = "Roseola",
            difficulty = Difficulty.Moderate,
            question = "An 8-month-old infant had a 4-day history of high fever (39.5°C) but appeared surprisingly playful (\"happy feigner\"). Today the fever suddenly dropped, and a rose-pink maculopapular rash appeared on the trunk. What is the diagnosis?",
            correctAnswer = "Roseola Infantum",
            explanation = "The sequence of high fever followed by sudden defervescence and arrival of a trunk-focused rash is classic for Roseola (HHV-6)."
        ),
        Question(
            id = "case-3",
            type = QuestionType.case,
            topic = "Meningococcal Disease",
            difficulty = Difficulty.Advanced,
            question = "A 17-year-old adolescent presents with sudden high fever, chills, and a rapidly spreading non-blanching petechial rash on the legs. Within hours, they are hypotensive and ill-appearing. What life-threatening condition must be treated empirically?",
            correctAnswer = "Meningococcal Disease",
            explanation = "Sudden high fever with a non-blanching petechial rash and rapid progression to shock is hallmark for meningococcemia."
        ),
        Question(
            id = "case-4",
            type = QuestionType.case,
            topic = "Scarlet Fever",
            difficulty = Difficulty.Moderate,
            question = "A 7-year-old has a sore throat, headache, and a diffuse red rash with a \"sandpaper\" texture. The rash is most prominent in the axillary and inguinal folds. The tongue is beefy red with enlarged papillae. Diagnosis?",
            correctAnswer = "Scarlet Fever",
            explanation = "Sandpaper rash, Pastia's lines (crease accentuation), and red strawberry tongue are diagnostic of Scarlet Fever."
        ),
        Question(
            id = "case-5",
            type = QuestionType.case,
            topic = "Varicella",
            difficulty = Difficulty.Moderate,
            question = "A 5-year-old child has an itchy rash that started on the chest and face. Exam shows red macules, clear vesicles, and some crusted lesions simultaneously. What is the diagnosis?",
            correctAnswer = "Varicella",
            explanation = "The hallmark of Varicella is the presence of lesions in multiple stages (macules, papules, \"dew drop\" vesicles, and crusts) concurrently."
        ),
        Question(
            id = "case-6",
            type = QuestionType.case,
            topic = "RMSF",
            difficulty = Difficulty.Advanced,
            question = "A child from North Carolina presents with high fever, severe headache, and myalgia. On day 3, a blanching rash appears on the wrists and ankles. History reveals a camping trip 1 week ago. Most likely diagnosis?",
            correctAnswer = "Rocky Mountain Spotted Fever",
            explanation = "Fever, headache, tick-prone area (NC), and a rash starting at the extremities (wrists/ankles) fit the Triad of RMSF."
        ),
        Question(
            id = "case-7",
            type = QuestionType.case,
            topic = "Measles",
            difficulty = Difficulty.Moderate,
            question = "An unvaccinated 4-year-old presents with fever, runny nose, harsh cough, and red eyes. Small blue-white spots are seen inside the cheeks. A day later, a maculopapular rash begins at the hairline and spreads downward. Diagnosis?",
            correctAnswer = "Measles",
            explanation = "The 3 Cs (Cough, Coryza, Conjunctivitis), Koplik spots, and cephalocaudal rash progression are classic for Measles."
        ),
        Question(
            id = "case-8",
            type = QuestionType.case,
            topic = "Fifth Disease",
            difficulty = Difficulty.Easy,
            question = "A 6-year-old child appears at the clinic with bright red cheeks and a \"slapped\" look. They are afebrile and otherwise feel well. A faint lacy rash is beginning on their arms. What is the diagnosis?",
            correctAnswer = "Fifth Disease",
            explanation = "The \"slapped-cheek\" appearance followed by a lacy, reticulated rash in an otherwise well-appearing child defines Fifth Disease."
        ),
        Question(
            id = "case-9",
            type = QuestionType.case,
            topic = "Rubella",
            difficulty = Difficulty.Moderate,
            question = "A pregnant teacher is exposed to a child with a 3-day fine pink rash and tender posterior auricular lymphadenopathy. Which diagnosis is of primary concern for the fetus?",
            correctAnswer = "Rubella",
            explanation = "Rubella is typically mild for children but poses a severe risk of Congenital Rubella Syndrome if contracted during pregnancy."
        ),
        Question(
            id = "case-10",
            type = QuestionType.case,
            topic = "Hand-Foot-Mouth Disease",
            difficulty = Difficulty.Easy,
            question = "A 3-year-old refuses to eat due to painful mouth sores. Small oval grayish vesicles are found on the palms, soles, and buttocks. Diagnosis?",
            correctAnswer = "Hand-Foot-Mouth Disease",
            explanation = "HFMD (Coxsackievirus) presents with oral ulcers and a characteristic vesicular exanthem on the hands, feet, and often the diaper area."
        ),

        // --- TRUE/FALSE QUESTIONS (10) ---
        Question(
            id = "tf-1",
            type = QuestionType.tf,
            topic = "Varicella",
            difficulty = Difficulty.Easy,
            question = "True or False: Aspirin should be used to control fever in Varicella.",
            correctAnswer = "False",
            explanation = "Aspirin must be avoided in Varicella (and Influenza) due to the high risk of Reye Syndrome."
        ),
        Question(
            id = "tf-2",
            type = QuestionType.tf,
            topic = "Fifth Disease",
            difficulty = Difficulty.Easy,
            question = "True or False: Children with Fifth Disease are most contagious after the lacy rash appears.",
            correctAnswer = "False",
            explanation = "Once the rash appears, the contagious period for Fifth Disease has usually passed."
        ),
        Question(
            id = "tf-3",
            type = QuestionType.tf,
            topic = "Kawasaki Disease",
            difficulty = Difficulty.Moderate,
            question = "True or False: Kawasaki disease is primarily a clinical diagnosis as there is no specific diagnostic test.",
            correctAnswer = "True",
            explanation = "Kawasaki is a clinical diagnosis based on the CRASH and Burn criteria and exclusion of other illnesses."
        ),
        Question(
            id = "tf-4",
            type = QuestionType.tf,
            topic = "Meningococcal Disease",
            difficulty = Difficulty.Easy,
            question = "True or False: All cases of meningococcal disease present with a petechial rash.",
            correctAnswer = "False",
            explanation = "Approximately 10–30% of meningococcal cases have NO rash, particularly in meningitis without septicemia."
        ),
        Question(
            id = "tf-5",
            type = QuestionType.tf,
            topic = "RMSF",
            difficulty = Difficulty.Moderate,
            question = "True or False: In RMSF, it is safe to wait for the petechial rash to appear before starting Doxycycline.",
            correctAnswer = "False",
            explanation = "Treatment should begin immediately on suspicion; waiting for the petechial rash (a late sign) increases mortality risk."
        ),
        Question(
            id = "tf-6",
            type = QuestionType.tf,
            topic = "Roseola",
            difficulty = Difficulty.Easy,
            question = "True or False: Roseola is primarily a disease of school-age children (5–12 years).",
            correctAnswer = "False",
            explanation = "Roseola (Exanthem Subitum) typically affects infants and toddlers (6–24 months)."
        ),
        Question(
            id = "tf-7",
            type = QuestionType.tf,
            topic = "Scarlet Fever",
            difficulty = Difficulty.Moderate,
            question = "True or False: Antibiotic treatment for Scarlet Fever prevents post-streptococcal glomerulonephritis.",
            correctAnswer = "False",
            explanation = "Antibiotics prevent Rheumatic Fever, but they do NOT prevent post-streptococcal glomerulonephritis."
        ),
        Question(
            id = "tf-8",
            type = QuestionType.tf,
            topic = "Measles",
            difficulty = Difficulty.Easy,
            question = "True or False: Pneumonia is the most common cause of measles-related death.",
            correctAnswer = "True",
            explanation = "Pneumonia is the most frequent and severe complication leading to fatality in Measles patients."
        ),
        Question(
            id = "tf-9",
            type = QuestionType.tf,
            topic = "Rubella",
            difficulty = Difficulty.Moderate,
            question = "True or False: Forchheimer spots are pathognomonic for Rubella.",
            correctAnswer = "False",
            explanation = "While seen in 20% of Rubella cases, Forchheimer spots (petechiae on soft palate) are not pathognomonic as they can appear in other conditions."
        ),
        Question(
            id = "tf-10",
            type = QuestionType.tf,
            topic = "Hand-Foot-Mouth Disease",
            difficulty = Difficulty.Easy,
            question = "True or False: Dehydration is the most common complication of Hand-Foot-Mouth Disease.",
            correctAnswer = "True",
            explanation = "Painful mouth sores often lead to reduced oral intake and resulting dehydration."
        )
    )
}
