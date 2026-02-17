package com.example.portfolio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.R
import com.example.portfolio.ui.theme.*

/* ---------------- DATA MODEL ---------------- */

data class SkillData(
    val imageIcon: Int,
    val name: String,
    val level: Float
)

/* ---------------- MAIN SCREEN ---------------- */

@Composable
fun SkillScreen() {

    var skills by remember {
        mutableStateOf(
            listOf(
                SkillData(R.drawable.python, "Python", 0.9f),
                SkillData(R.drawable.ml, "Machine Learning", 0.85f),
                SkillData(R.drawable.dl, "Deep Learning", 0.7f),
                SkillData(R.drawable.java, "Java", 0.7f),
                SkillData(R.drawable.exploration, "Data Analysis", 0.75f),
                SkillData(R.drawable.android, "Android Development", 0.65f),

            )
        )
    }

    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var selectedSkill by remember { mutableStateOf<SkillData?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(40.dp))
            ProfileHeader()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = "My Skills",
                    fontSize = 20.sp,
                    color = blue,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp, 4.dp, 4.dp, 120.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(skills.size) {
                        SkillCard(
                            skill = skills[it],
                            onLongPress = {
                                selectedSkill = skills[it]
                            }
                        )
                    }
                }
            }
        }

        /* ---------- ADD BUTTON ---------- */

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showAddDialog = true },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = blue)
            ) {
                Text(
                    text = "+ Add Skill",
                    modifier = Modifier.padding(16.dp),
                    color = white,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        /* ---------- EDIT FLOATING BUTTON ---------- */

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 96.dp)
        ) {
            Card(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        if (selectedSkill != null) {
                            showEditDialog = true
                        }
                    },
                shape = RoundedCornerShape(50),
                colors = CardDefaults.cardColors(containerColor = black),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Skill",
                        tint = white
                    )
                }
            }
        }

        /* ---------- ADD DIALOG ---------- */

        if (showAddDialog) {
            AddEditSkillDialog(
                onDismiss = { showAddDialog = false },
                onSave = { newSkill ->
                    skills = skills + newSkill
                    showAddDialog = false
                }
            )
        }

        /* ---------- EDIT DIALOG ---------- */

        if (showEditDialog && selectedSkill != null) {
            AddEditSkillDialog(
                existingSkill = selectedSkill,
                onDismiss = {
                    showEditDialog = false
                    selectedSkill = null
                },
                onSave = { updatedSkill ->
                    skills = skills.map {
                        if (it == selectedSkill) updatedSkill else it
                    }
                    showEditDialog = false
                    selectedSkill = null
                }
            )
        }
    }
}

/* ---------------- SKILL CARD ---------------- */

@Composable
fun SkillCard(
    skill: SkillData,
    onLongPress: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onLongClick = onLongPress
            ),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = lightGrey)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(lightblue2),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(skill.imageIcon),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = skill.name,
                fontSize = 16.sp,
                color = black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { skill.level },
                modifier = Modifier.fillMaxWidth(),
                color = blue,
                trackColor = lightgray,
                strokeCap = StrokeCap.Square
            )

            Text(
                text = "${(skill.level * 100).toInt()}%",
                fontSize = 12.sp,
                color = darkgray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

/* ---------------- ADD / EDIT DIALOG ---------------- */

@Composable
fun AddEditSkillDialog(
    existingSkill: SkillData? = null,
    onDismiss: () -> Unit,
    onSave: (SkillData) -> Unit
) {
    var name by remember { mutableStateOf(existingSkill?.name ?: "") }
    var level by remember { mutableStateOf(existingSkill?.level ?: 0.5f) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(if (existingSkill == null) "Add Skill" else "Edit Skill")
        },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Skill Name") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Skill Level: ${(level * 100).toInt()}%")
                Slider(
                    value = level,
                    onValueChange = { level = it },
                    valueRange = 0f..1f
                )
            }
        },
        confirmButton = {
            Text(
                "Save",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onSave(
                            SkillData(
                                imageIcon = R.drawable.skill,
                                name = name,
                                level = level
                            )
                        )
                    },
                fontWeight = FontWeight.Bold,
                color = blue
            )
        },
        dismissButton = {
            Text(
                "Cancel",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDismiss() },
                color = black
            )
        }
    )
}
