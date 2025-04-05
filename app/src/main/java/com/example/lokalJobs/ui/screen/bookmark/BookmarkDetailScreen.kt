package com.example.lokalJobs.ui.screen.bookmark

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.lokalJobs.R
import com.example.lokalJobs.data.local.modal.BookmarkJob

import com.example.lokalJobs.ui.theme.BorderGreen
import com.example.lokalJobs.ui.theme.GoodBlue
import com.example.lokalJobs.ui.theme.GoodYellow
import com.example.lokalJobs.ui.theme.LightGreen
import com.example.lokalJobs.ui.theme.LightYellow


@Composable
fun BookmarkDetailScreen(
    job: BookmarkJob
) {

    val context = LocalContext.current

    val imageRequest = ImageRequest.Builder(context)
        .data(job.creatives[0].file)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onPrimary)
    ) {
        item {
            AsyncImage(
                model = imageRequest,
                contentDescription = "Job image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
            )


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(text = job.job_role, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Filled.BookmarkAdd, contentDescription = null)
            }

            val salaryText = when {
                job.salary_min != 0 && job.salary_max != 0 -> "₹${job.salary_min} - ${job.salary_max}"
                else -> "Depends on Experience/Position"
            }

            Text(
                text = salaryText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            com.example.lokalJobs.ui.screen.Job.IconWithText(
                image = R.drawable.officebuilding,
                text = job.company_name,
                textSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            com.example.lokalJobs.ui.screen.Job.IconWithText(
                text = job.job_location_slug,
                textSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Row for Tags Chips
            TagChipRow(
                job = job,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            JobHighlightsAndPrefernce(job)

            Spacer(modifier = Modifier.height(32.dp))

            JobDescriptionRow(job = job, modifier = Modifier.padding(horizontal = 8.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Call Hr
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(42.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(LightYellow)
                    .clickable {
                        Toast.makeText(context, "Calling HR...", Toast.LENGTH_SHORT).show()
                    }
                    .border(1.dp, GoodYellow, RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "\uD83D\uDCDE  Call HR",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            // Whatsapp and FAQ
            WhatsAppCard(job)




            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Composable
fun IconWithText2(
    icon: Int,
    text: String,
    tint: Color = Color.Black,
    center: Boolean = false,
    rowModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = if (center) Arrangement.Center else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = rowModifier.clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = tint,
            modifier = iconModifier
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = textModifier
        )
    }
}

@Composable
private fun JobDescriptionRow(
    job: BookmarkJob,
    modifier: Modifier = Modifier
) {

    Text(
        text = "Job Descriptions",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(8.dp))
    //other details
    Row {
        Text(
            text = "Experience: ${job?.other_details}",
            fontSize = 14.sp,
            modifier = modifier
        )
    }
}

@Composable
private fun JobHighlightsAndPrefernce(job: BookmarkJob) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(234,234,234))
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Job Highlights", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            IconKeyValueText(
                icon = Icons.Outlined.Star,
                keyText = "Experience: ",
                valueText = job.primary_details.Experience
            )
            IconKeyValueText(
                icon = Icons.AutoMirrored.Outlined.MenuBook,
                keyText = "Qualification: ",
                valueText = job.primary_details.Qualification
            )

            IconKeyValueText(
                icon = Icons.Outlined.PeopleAlt,
                keyText = "Gender: ",
                valueText = job.V3[1].field_value
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Preferences", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            IconKeyValueText(
                icon = Icons.Outlined.WbSunny,
                keyText = "Shift timing: ",
                valueText = job.V3[2].field_value
            )


        }
    }
}

@Composable
private fun SharesAndLikes(job: BookmarkJob) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row {
            Icon(
                imageVector = Icons.Filled.Facebook,
                contentDescription = "FaceBook View",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "${job?.fb_shares ?: 0}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }

        Row {
            Icon(
                imageVector = Icons.Filled.RemoveRedEye,
                contentDescription = "Views",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "${job?.views ?: 0}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}


@Composable
fun WhatsAppCard(job: BookmarkJob?) {
    val context = LocalContext.current
    val whatsappLink = job?.contact_preference?.whatsapp_link

    val contactTimeText = "* Contact us between " +
            (job?.contact_preference?.preferred_call_start_time ?: "Whatsapp Time") +
            " to ${job?.contact_preference?.preferred_call_end_time ?: "Whatsapp Time"}"

    Column {
        IconWithText2(
            icon = R.drawable.whatsapp,
            text = "Talk with us",
            tint = Green,
            center = true,
            rowModifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .fillMaxWidth()
                .height(42.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(LightGreen)
                .border(1.dp, BorderGreen, RoundedCornerShape(10.dp)),
            iconModifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp),
            onClick = {
                whatsappLink?.let { openLinkInBrowser(context, it) }
            }
        )

        Text(
            text = contactTimeText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            lineHeight = 10.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )

    }
}

fun openLinkInBrowser(context: Context, link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    ContextCompat.startActivity(context, intent, null)
}

@Composable
fun IconKeyValueText(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    keyText: String,
    valueText: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = GoodBlue,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = keyText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = valueText,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}
