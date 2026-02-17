package com.example.portfolio.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.*


data class Project(
    val name: String,
    val techStack: String,
    val description: String,
    val completed: Boolean
)


@Composable
fun ProjectScreen() {

    var selectedCategory by remember { mutableStateOf("Completed") }
    var projects by remember { mutableStateOf(ProjectList) }

    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var selectedProject by remember { mutableStateOf<Project?>(null) }

    val filteredProjects = projects.filter {
        if (selectedCategory == "Completed") it.completed else !it.completed
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(40.dp))
            ProfileHeader()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                ProjectCategory(
                    selectedCategory = selectedCategory,
                    onCategoryChange = { selectedCategory = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 120.dp)
                ) {
                    items(filteredProjects.size) {
                        ProjectCard(
                            project = filteredProjects[it],
                            onLongPress = {
                                selectedProject = filteredProjects[it]
                            }
                        )
                    }
                }
            }
        }

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
                    text = "+ Add Project",
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(),
                    color = white,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 96.dp)
        ) {
            Card(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        if (selectedProject != null) {
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
                        modifier = Modifier.fillMaxWidth()
                            .clip(CircleShape),
                        contentDescription = "Edit Project",
                        tint = white
                    )
                }
            }
        }

        if (showAddDialog) {
            AddEditProjectDialog(
                onDismiss = { showAddDialog = false },
                onSave = { newProject ->
                    projects = projects + newProject
                    showAddDialog = false
                }
            )
        }

        if (showEditDialog && selectedProject != null) {
            AddEditProjectDialog(
                existingProject = selectedProject,
                onDismiss = {
                    showEditDialog = false
                    selectedProject = null
                },
                onSave = { updatedProject ->
                    projects = projects.map {
                        if (it == selectedProject) updatedProject else it
                    }
                    showEditDialog = false
                    selectedProject = null
                }
            )
        }
    }
}

@Composable
fun ProjectCategory(
    selectedCategory: String,
    onCategoryChange: (String) -> Unit
) {
    TabRow(
        selectedTabIndex = if (selectedCategory == "Completed") 0 else 1,
        containerColor = white,
        contentColor = blue
    ) {
        Tab(
            selected = selectedCategory == "Completed",
            onClick = { onCategoryChange("Completed") },
            text = { Text("Completed") }
        )
        Tab(
            selected = selectedCategory == "In Progress",
            onClick = { onCategoryChange("In Progress") },
            text = { Text("In Progress") }
        )
    }
}

@Composable
fun ProjectCard(
    project: Project,
    onLongPress: () -> Unit
) {
    val backgroundColor = if (project.completed) lightGreen else lightAmber
    val statusColor = if (project.completed) greentxt else ambertxt
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { expanded = !expanded },
                onLongClick = { onLongPress() }
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Column {

            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        project.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = black
                    )
                    Text(
                        if (project.completed) "Completed" else "In Progress",
                        color = statusColor
                    )
                }

                Icon(
                    imageVector = if (expanded)
                        Icons.Default.KeyboardArrowUp
                    else
                        Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(tween(200)),
                exit = shrinkVertically(tween(200))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Divider(color = lightgray3)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Tech Stack:", fontWeight = FontWeight.Bold)
                    Text(project.techStack)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Description:", fontWeight = FontWeight.Bold)
                    Text(project.description)
                }
            }
        }
    }
}

@Composable
fun AddEditProjectDialog(
    existingProject: Project? = null,
    onDismiss: () -> Unit,
    onSave: (Project) -> Unit
) {
    var name by remember { mutableStateOf(existingProject?.name ?: "") }
    var tech by remember { mutableStateOf(existingProject?.techStack ?: "") }
    var desc by remember { mutableStateOf(existingProject?.description ?: "") }
    var completed by remember { mutableStateOf(existingProject?.completed ?: false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (existingProject == null) "Add Project" else "Edit Project") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Project Name") })
                Spacer(Modifier.height(8.dp))
                TextField(value = tech, onValueChange = { tech = it }, label = { Text("Tech Stack") })
                Spacer(Modifier.height(8.dp))
                TextField(value = desc, onValueChange = { desc = it }, label = { Text("Description") })
                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Completed")
                    Spacer(Modifier.weight(1f))
                    Switch(checked = completed, onCheckedChange = { completed = it })
                }
            }
        },
        confirmButton = {
            Text(
                "Save",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onSave(Project(name, tech, desc, completed))
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

val ProjectList = listOf(
    Project(
        "Sentiment Analysis",
        "Python, ML, DL",
        "Real-time sentiment analysis app",
        true
    ),
    Project(
        "Facial Emotion Recognition",
        "ML, DL, OpenCV",
        "Emotion detection using camera",
        true
    ),
    Project(
        "Portfolio App",
        "Kotlin, Jetpack Compose",
        "Portfolio management app",
        true
    ),
    Project(
        "PhoneDoctor",
        "Kotlin, API Integration, Python",
        "AI-enabled healthcare mobile app",
        true
    ),
    Project(
        "Object Detection",
        "Python, YOLO, OpenCV",
        "Object detection using camera and images",
        true
    ),
    Project(
        "StudyNotes",
        "Kotlin, Room Database, Firebase",
        "A mobile application to store and fetch study materials",
        false
    )
)
