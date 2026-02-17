package com.example.portfolio.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.R
import com.example.portfolio.ui.theme.*

@Composable
fun ContactScreen() {

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(40.dp))
        ProfileHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Contact Me",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = blue
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 120.dp)
            ) {

                item {
                    ContactItem("Email", "abhishe356k@gmail.com")
                }

                item {
                    ContactItem("Phone", "+91 8556971646")
                }

                item {
                    ContactItem("Address", "Phagwara, Punjab, India")
                }

                item {
                    SocialLinkItem(
                        title = "GitHub",
                        link = "https://github.com/abhishek1-3",
                        icon = R.drawable.github
                    )
                }

                item {
                    SocialLinkItem(
                        title = "LinkedIn",
                        link = "https://www.linkedin.com/in/abhishek-kumar0212",
                        icon = R.drawable.linkedin
                    )
                }
            }
        }
    }
}

/* ---------------- NORMAL CONTACT ITEM ---------------- */

@Composable
fun ContactItem(
    title: String,
    value: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = lightGrey),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = title, fontSize = 14.sp, color = darkgray)

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = black
            )
        }
    }
}

/* ---------------- SOCIAL LINK WITH ICON ---------------- */

@Composable
fun SocialLinkItem(
    title: String,
    link: String,
    icon: Int
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = lightGrey),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(link)
                        )
                        context.startActivity(intent)
                    }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = darkgray
                )

                Text(
                    text = link,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = blue,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}
