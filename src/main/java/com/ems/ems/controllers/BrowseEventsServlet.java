package com.ems.ems.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ems.ems.models.Event;
import com.ems.ems.services.EventService;

import java.io.IOException;
import java.util.List;

@WebServlet("/browse-events")
public class BrowseEventsServlet extends HttpServlet {

    @Inject
    private EventService eventService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        List<Event> events;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            events = eventService.searchEvents(searchQuery); // Assumes EventService has a searchEvents method
        } else {
            events = eventService.getAllEvents(); // Fetch all events
        }

        request.setAttribute("events", events);
        request.getRequestDispatcher("/views/browse-events.jsp").forward(request, response);
    }
}