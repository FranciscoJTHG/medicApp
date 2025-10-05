import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.teleMedicina.teleMedicina.services.DoctorService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(DoctorController.class)
@AutoConfigureMockMvc(addFilters = false) 
@Import({SecurityConfigurations.class})
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    public void testGetAllDoctors() throws Exception {
        // Mock service response
        List<DoctorDto> mockDoctors = Arrays.asList(
            new DoctorDto(1L, "Dr. John Doe", "Cardiología"),
            new DoctorDto(2L, "Dr. Jane Smith", "Dermatología")
        );
        when(doctorService.getAllDoctors()).thenReturn(mockDoctors);

        mockMvc.perform(get("/api/doctores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetDoctorById() throws Exception {
        Long doctorId = 1L;
        DoctorDto mockDoctor = new DoctorDto(doctorId, "Dr. John Doe", "Cardiología");
        when(doctorService.getDoctorById(doctorId)).thenReturn(mockDoctor);

        mockMvc.perform(get("/api/doctores/{id}", doctorId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetDoctorsBySpecialty() throws Exception {
        String specialty = "Cardiología";
        List<DoctorDto> mockDoctors = Arrays.asList(
            new DoctorDto(1L, "Dr. John Doe", specialty)
        );
        when(doctorService.getDoctorsBySpecialty(specialty)).thenReturn(mockDoctors);

        mockMvc.perform(get("/api/doctores/especialidad/{especialidad}", specialty))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
