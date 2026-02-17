package com.example.portfolio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.R
import com.example.portfolio.ui.theme.black
import com.example.portfolio.ui.theme.blue
import com.example.portfolio.ui.theme.darkgray
import com.example.portfolio.ui.theme.gray
import com.example.portfolio.ui.theme.lightgray
import com.example.portfolio.ui.theme.lightgray3
import com.example.portfolio.ui.theme.white

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Fixed Profile Header
        ProfileHeader()

        // Scrollable Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                SectionTitle("Bio")
                ContentCard {
                    Text(
                        text = "Hello! My name is Abhishek Kumar. I'm a B. Tech Computer Science student specializing in Data Science. I have hands-on experience in machine learning, deep learning and computer vision through internships and projects. I am interested in AI engineering roles where I can build real-world, impactful AI solutions.",
                        fontSize = 15.sp,
                        lineHeight = 24.sp,
                        color = black
                    )
                }
            }

            item {
                SectionTitle("Education")
                ContentCard {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Bachelor of Technology - Computer Science",
                            color = black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "GNA University",
                            color = darkgray,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "2022 - 2026",
                            color = gray,
                            fontSize = 14.sp
                        )

                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = lightgray3
                        )

                        Text(
                            text = "Senior Secondary Examination (10+2) (PSEB)",
                            color = black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Shri Mahavir Jain Model Senior Secondary School",
                            color = darkgray,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "2020 - 2021",
                            color = gray,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            item {
                SectionTitle("Achievements")
                ContentCard {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                        AchievementItem("Collaborated on mini-projects simulating industry-level problem statements.")
                        AchievementItem("Created PhoneDoctor, an AI-enabled healthcare mobile app integrating Nearby hospital" +
                                "& pharmacy locator, AI assistant for first aid and symptom based disease recognition.")
                        AchievementItem("Attended 8+ technical workshops on AI, LLMs, and Web Development.")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ProfileHeader() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = cardColors(containerColor = white),
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Abhishek Kumar",
                color = blue,
                style = typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Data Science and Machine Learning Engineer",
                color = darkgray,
                style = typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = blue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun ContentCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = cardColors(containerColor = lightgray),
        elevation = cardElevation(2.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun AchievementItem(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "→",
            fontSize = 16.sp,
            color = black,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            color = black
        )
    }
}
