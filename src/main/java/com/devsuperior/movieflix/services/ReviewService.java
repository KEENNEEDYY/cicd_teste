package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		
		try {
			
			Review entity = new Review();
			
			entity.setText(dto.getText());
			entity.setMovie(movieRepository.getOne(dto.getMovieId()));
			entity.setUser(userService.contextUser());
			
			entity = repository.save(entity);
			
			return new ReviewDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("User not found");
		}		
	}

	public List<ReviewDTO> findMovieReviewsById(Long movieId) {
		Movie entity = movieRepository.getOne(movieId);
		List<Review> list = repository.findMovieReviewsById(entity);
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}

}
