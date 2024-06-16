package be.cbconnectit.portfolio.app.domain.model

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test


class TestimonialTest {
    private val jobPosition = JobPosition("", "Customer/Testing Engineer", "", "")
    private val company = Company("", "C.B. Connect I.T.", emptyList(), "", "")

    @Test
    fun testStyledSubtitle() {
        val color = Color.Red
        val delimiter = "at"

        val testimonial = Testimonial("", "", "", company, jobPosition, "", "", "")
        val result = testimonial.styledSubtitle(color, delimiter)

        // Verify text only has 2 spans, for company and jobPosition
        assertEquals(2, result.spanStyles.size)

        // Verify text has the correct color as a span
        assertEquals(color, result.spanStyles.first().item.color)
        assertEquals(color, result.spanStyles.last().item.color)

        // Verify that span is on the correct Text
        assertEquals(jobPosition.name, result.text.substring(result.spanStyles.first().start, result.spanStyles.first().end))
        assertEquals(company.name, result.text.substring(result.spanStyles.last().start, result.spanStyles.last().end))

        // Verify that the delimiter is between both spans
        assertEquals(delimiter, result.text.substring(result.spanStyles.first().end + 1, result.spanStyles.last().start - 1))
    }

    @Test
    fun testStyledSubtitleWhenCompanyIsNotKnown() {
        val color = Color.Red
        val delimiter = "at"

        val testimonial = Testimonial("", "", "", null, jobPosition, "", "", "")
        val result = testimonial.styledSubtitle(color, delimiter)

        // Verify text only has 2 spans, for company and jobPosition
        assertEquals(1, result.spanStyles.size)

        // Verify text has the correct color as a span
        assertEquals(color, result.spanStyles.first().item.color)

        // Verify that span is on the correct Text
        assertEquals(jobPosition.name, result.text.substring(result.spanStyles.first().start, result.spanStyles.first().end))

        // Verify that text does not contain delimiter
        assert(!result.text.contains(delimiter))
    }
}