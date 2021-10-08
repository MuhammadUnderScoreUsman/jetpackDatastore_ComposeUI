package com.mohammadosman.jetpackdatastoresample_composeui.presentation.ui.task

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mohammadosman.jetpackdatastoresample_composeui.R
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.FilterOrder
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.TaskViewModel

@ExperimentalAnimationApi
@Composable
fun TaskScreen(
    viewmodel: TaskViewModel
) {
    val list by viewmodel.tasks.collectAsState(initial = emptyList())
    val filter by viewmodel.filter.collectAsState(FilterOrder.UNFILTERED)
    val enableState = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            FilterActionButton(
                onShowFilter = {
                    enableState.value = !enableState.value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End),
                isEnable = enableState.value,
            ) {
                FilterCheckBoxes(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .align(Alignment.CenterHorizontally),
                    isCompletedEnable = filter == FilterOrder.COMPLETED,
                    onCompletedCheckChanged = {
                        viewmodel.updateFilter(filter = FilterOrder.COMPLETED)
                    },
                    isImportantEnable = filter == FilterOrder.IMPORTANT,
                    onImportantCheckChanged = {
                        viewmodel.updateFilter(filter = FilterOrder.IMPORTANT)
                    },
                    isUnFilteredEnable = filter == FilterOrder.UNFILTERED,
                    onUnFilteredCheckChanged = {
                        viewmodel.updateFilter(filter = FilterOrder.UNFILTERED)
                    },
                )
            }

            LazyColumn {
                items(list) { tsk ->
                    TaskCard(
                        title = tsk.title, body = tsk.body,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        isImportant = tsk.isImportant
                    )
                }
            }
        }
    }
}

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    isImportant: Boolean
) {
    Card(
        modifier = modifier,
        elevation = 6.dp,
    ) {
        TaskDetail(
            title = title, body = body,
            isImportant = isImportant
        )
    }
}

@Composable
fun FilterCheckBoxes(
    modifier: Modifier = Modifier,
    isCompletedEnable: Boolean,
    onCompletedCheckChanged: (Boolean) -> Unit,
    isImportantEnable: Boolean,
    onImportantCheckChanged: (Boolean) -> Unit,
    isUnFilteredEnable: Boolean,
    onUnFilteredCheckChanged: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TaskCheckBox(
            isEnable = isCompletedEnable,
            onCheckChanged = onCompletedCheckChanged,
            boxType = "Completed",
        )

        TaskCheckBox(
            isEnable = isImportantEnable,
            onCheckChanged = onImportantCheckChanged,
            boxType = "Important",
        )

        TaskCheckBox(
            isEnable = isUnFilteredEnable,
            onCheckChanged = onUnFilteredCheckChanged,
            boxType = "Unfiltered",
        )
    }
}

@Composable
fun TaskDetail(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    isImportant: Boolean
) {

    Row(
        modifier = modifier.padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .padding(2.dp)
        ) {

            // title
            TaskTextView(
                textStyle = if (isImportant) MaterialTheme.typography.h4 else MaterialTheme.typography.h6,
                text = title
            )

            // body
            TaskTextView(
                textStyle = MaterialTheme.typography.subtitle2,
                text = body
            )
        }
        if (isImportant) {
            IsImportantTask(
                modifier = Modifier
                    .align(Alignment.CenterVertically)

            )
        }
    }
}

@Composable
fun IsImportantTask(
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_imp),
        contentDescription = null,
        tint = Color.Red,
        modifier = modifier
    )
}

@Composable
fun TaskTextView(
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    text: String
) {
    Text(
        text = text,
        modifier = modifier,
        style = textStyle
    )
}

@Composable
fun TaskCheckBox(
    modifier: Modifier = Modifier,
    isEnable: Boolean,
    onCheckChanged: (Boolean) -> Unit,
    boxType: String
) {
    Row(modifier = modifier) {
        Checkbox(
            checked = isEnable,
            onCheckedChange = onCheckChanged
        )
        Text(text = boxType, modifier = Modifier.padding(2.dp))
        Spacer(
            modifier = Modifier
                .padding(6.dp)
        )
    }
}


@ExperimentalAnimationApi
@Composable
fun FilterActionButton(
    modifier: Modifier = Modifier,
    onShowFilter: () -> Unit,
    isEnable: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    Column(modifier = modifier) {
        IconButton(
            onClick = onShowFilter,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                tint = Color.Black
            )
        }
        AnimatedVisibility(visible = isEnable) {
            content()
        }
    }
}