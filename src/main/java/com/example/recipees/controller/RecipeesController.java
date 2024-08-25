package com.example.recipees.controller;

import com.example.recipees.dto.RecipeesDTO;
import com.example.recipees.jwt.JwtTokenService;
import com.example.recipees.models.Products;
import com.example.recipees.services.RecipeesService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/recipees")
public class RecipeesController {

    @Autowired
    RecipeesService recipeesService;

    @Autowired
    JwtTokenService jwtTokenService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public RecipeesDTO createRecipee(
            @RequestBody RecipeesDTO newRecipee, @RequestHeader("Authorization") String authorizationHeader)
            throws Exception {
        String token = extractTokenFromHeader(authorizationHeader);
        String username = jwtTokenService.getUsernameFromToken(token);
        newRecipee.setCreatedBy(username);

        return recipeesService.createRecipee(newRecipee);
    }

    @GetMapping(value = "/{mailUser}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RecipeesDTO> getRecipeesByUser(@PathVariable("mailUser") String usermail) {
        return recipeesService.getRecipeesByUser(usermail);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public RecipeesDTO recipee (
            @RequestBody RecipeesDTO editRecipee)
            throws Exception {
        return recipeesService.editRecipee(editRecipee);
    }

    @DeleteMapping(value={"/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RecipeesDTO recipee(
            @PathVariable("id") String recipeeId) {
        return recipeesService.deleteRecipee(recipeeId);
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void generatePDF(@PathVariable("id") String recipeeId, HttpServletResponse response) {
        RecipeesDTO recipeesDTO = recipeesService.getRecipeeById(recipeeId);

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                // Recipe Name
                contentStream.showText("Recipe Name: " + recipeesDTO.getNombre());
                contentStream.newLineAtOffset(0, -15);

                // Ingredients Title
                contentStream.showText("Ingredientes:");
                contentStream.newLineAtOffset(0, -15);

                // Ingredients List
                List<Products> ingredients = recipeesDTO.getProducts();
                for (Products ingredient : ingredients) {
                    contentStream.showText("- " + ingredient.getNombre() + ": " + ingredient.getCantidad() + " " + ingredient.getUnidadMedida());
                    contentStream.newLineAtOffset(0, -15);
                }

                // Recipe Steps Title
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Pasos Receta:");
                contentStream.newLineAtOffset(0, -15);

                // Recipe Steps
                String[] steps = recipeesDTO.getSteps().split("\n"); // Split steps by line breaks
                for (String step : steps) {
                    contentStream.showText(step);
                    contentStream.newLineAtOffset(0, -15); // Move to the next line
                }

                contentStream.endText();
            }

            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename=recipe_" + recipeesDTO.getId() + ".pdf");
            document.save(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
