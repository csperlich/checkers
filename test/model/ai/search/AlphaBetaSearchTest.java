package model.ai.search;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import model.Board;
import model.Move;
import model.MoveInterface;
import model.MultiJump;
import model.PieceColor;
import model.SingleJump;
import model.Strategy;
import model.ai.evaluation.BoardEvaluatorInterface;
import model.ai.evaluation.BoardEvaluatorSummator;
import model.ai.evaluation.KingCountEvaluator;
import model.ai.evaluation.PawnCountEvaluator;

public class AlphaBetaSearchTest {

    @Test
    public void testGetBestMove_PieceCountEvaluator_LightComplexity() {
        final Board board = new Board(Arrays.asList(1, 7, 10, 11), Arrays.asList(14, 16, 22, 25));

        final HashMap<BoardEvaluatorInterface, Double> weightMap = new HashMap<>();
        weightMap.put(PawnCountEvaluator.getInstance(), 1.0);
        weightMap.put(KingCountEvaluator.getInstance(), 1.0);
        final Strategy strategy = new Strategy(new BoardEvaluatorSummator(), PieceColor.BLACK,
                weightMap);
        final AlphaBetaSearch searcher = new AlphaBetaSearch(board, strategy, 8);

        final MoveInterface expectedBestMove = new MultiJump(10, 26, Arrays.asList(17), board);
        final MoveInterface actualBestMove = searcher.alphaBetaSearch();

        assertEquals(expectedBestMove, actualBestMove);
    }

    @Test(timeout = 5000)
    public void testGetBestMove_PieceCountEvaluator_ManyKingsHighBranchFactor() {
        final Board board = new Board(Arrays.asList(5, 6, 7, 8), Arrays.asList(25, 26, 27, 28));
        board.getPiece(5).kingMe();
        board.getPiece(6).kingMe();
        board.getPiece(7).kingMe();
        board.getPiece(8).kingMe();
        board.getPiece(25).kingMe();
        board.getPiece(26).kingMe();
        board.getPiece(27).kingMe();
        board.getPiece(28).kingMe();

        final HashMap<BoardEvaluatorInterface, Double> weightMap = new HashMap<>();
        weightMap.put(PawnCountEvaluator.getInstance(), 1.0);
        final Strategy strategy = new Strategy(new BoardEvaluatorSummator(), PieceColor.BLACK,
                weightMap);
        final AlphaBetaSearch searcher = new AlphaBetaSearch(board, strategy, 8);

        searcher.alphaBetaSearch();
    }

    @Test
    public void testGetBestMove_PieceCountEvaluator_MaximumComplexity() {
        final Board board = new Board(Arrays.asList(1, 4, 6, 9, 10, 11, 13, 16, 23, 32),
                Arrays.asList(7, 14, 15, 22, 24, 25, 26, 27, 30, 31));
        board.getPiece(7).kingMe();
        board.getPiece(23).kingMe();
        board.getPiece(32).kingMe();
        board.getPiece(14).kingMe();

        final HashMap<BoardEvaluatorInterface, Double> weightMap = new HashMap<>();
        weightMap.put(PawnCountEvaluator.getInstance(), 1.0);
        final Strategy strategy = new Strategy(new BoardEvaluatorSummator(), PieceColor.BLACK,
                weightMap);
        final AlphaBetaSearch searcher = new AlphaBetaSearch(board, strategy, 8);

        final MoveInterface expectedBestMove = new MultiJump(10, 28, Arrays.asList(19), board);
        final MoveInterface actualBestMove = searcher.alphaBetaSearch();

        assertEquals(expectedBestMove, actualBestMove);
    }

    @Test
    public void testGetBestMove_PieceCountEvaluator_MediumComplexity() {
        final Board board = new Board(Arrays.asList(1, 4, 9, 10, 11, 16, 23),
                Arrays.asList(7, 14, 15, 20, 22, 25, 26, 27));
        board.getPiece(7).kingMe();
        board.getPiece(23).kingMe();

        final HashMap<BoardEvaluatorInterface, Double> weightMap = new HashMap<>();
        weightMap.put(PawnCountEvaluator.getInstance(), 1.0);
        final Strategy strategy = new Strategy(new BoardEvaluatorSummator(), PieceColor.BLACK,
                weightMap);
        final AlphaBetaSearch searcher = new AlphaBetaSearch(board, strategy, 8);

        final MoveInterface expectedBestMove = new MultiJump(23, 21, Arrays.asList(30), board);
        final MoveInterface actualBestMove = searcher.alphaBetaSearch();

        assertEquals(expectedBestMove, actualBestMove);
    }

    @Test
    public void testGetBestMove_PieceCountEvaluator_NoComplexity() {

        final Board board = new Board(Arrays.asList(1, 2, 3, 4, 12),
                Arrays.asList(29, 30, 31, 32, 16));

        final HashMap<BoardEvaluatorInterface, Double> weightMap = new HashMap<>();
        weightMap.put(PawnCountEvaluator.getInstance(), 1.0);
        final Strategy strategy = new Strategy(new BoardEvaluatorSummator(), PieceColor.BLACK,
                weightMap);
        final AlphaBetaSearch searcher = new AlphaBetaSearch(board, strategy, 1);

        final MoveInterface expectedBestMove = new SingleJump(12, 19, board);
        final MoveInterface actualBestMove = searcher.alphaBetaSearch();

        assertEquals(expectedBestMove, actualBestMove);
    }

    @Test
    public void testGetMove_baitTheTripleJump() {
        final Board board = new Board(Arrays.asList(1, 6, 9), Arrays.asList(11, 17, 19, 27));

        final HashMap<BoardEvaluatorInterface, Double> weightMap = new HashMap<>();
        weightMap.put(PawnCountEvaluator.getInstance(), 1.0);
        final Strategy strategy = new Strategy(new BoardEvaluatorSummator(), PieceColor.BLACK,
                weightMap);
        final AlphaBetaSearch searcher = new AlphaBetaSearch(board, strategy, 3);

        final MoveInterface expectedBestMove = new Move(9, 14, board);
        final MoveInterface actualBestMove = searcher.alphaBetaSearch();

        assertEquals(expectedBestMove, actualBestMove);
    }
}
